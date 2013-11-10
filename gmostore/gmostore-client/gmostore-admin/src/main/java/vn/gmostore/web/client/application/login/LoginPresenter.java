package vn.gmostore.web.client.application.login;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dto.CurrentUserDto;
import vn.gmostore.basic.dto.PlatformDto;
import vn.gmostore.web.client.application.ApplicationPresenter;
import vn.gmostore.web.client.application.event.ActionBarVisibilityEvent;
import vn.gmostore.web.client.application.event.DisplayMessageEvent;
import vn.gmostore.web.client.application.event.UserLoginEvent;
import vn.gmostore.web.client.application.widget.message.Message;
import vn.gmostore.web.client.application.widget.message.MessageStyle;
import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.resources.LoginMessages;
import vn.gmostore.web.client.rest.ProductResourceAsync;
import vn.gmostore.web.client.security.CurrentUser;
import vn.gmostore.web.shared.dispatch.LogInRequest;

import com.google.common.base.Strings;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.PlaceRequest.Builder;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> implements LoginUiHandlers {

    public interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
        void setLoginButtonEnabled(boolean enabled);
    }

    @ProxyStandard
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    public static final String LOGIN_COOKIE_NAME = "LoggedInCookie";

    private static final Logger logger = Logger.getLogger(LoginPresenter.class.getName());
    private final PlaceManager placeManager;
    //    private final DispatchAsync dispatchAsync;
    //    private final SessionService sessionService;
    //    private final ProductService productService;
    private final ProductResourceAsync productResourceAsync;
    private final CurrentUser currentUser;
    private final LoginMessages messages;

    @Inject
    LoginPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, CurrentUser currentUser, LoginMessages messages,
            ProductResourceAsync productResourceAsync) {
        super(eventBus, view, proxy);

        this.placeManager = placeManager;
        //        this.dispatchAsync = dispatchAsync;
        //        this.sessionService = sessionService;
        this.currentUser = currentUser;
        this.messages = messages;
        //        this.productService = productService;
        this.productResourceAsync = productResourceAsync;

        getView().setUiHandlers(this);
    }

    @Override
    public void login(String username, String password) {
        LogInRequest loginRequest = new LogInRequest(username, password);
        callServerLoginAction(loginRequest);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.SLOT_MAIN_CONTENT, this);
    }

    @Override
    protected void onReveal() {
        ActionBarVisibilityEvent.fire(this, false);

        if (!Strings.isNullOrEmpty(getLoggedInCookie())) {
            tryLoggingInWithCookieFirst();
        }
    }

    private void callServerLoginAction(LogInRequest loginRequest) {
        productResourceAsync.getById(2, new MethodCallback<GetResult<PlatformDto>>() {

            @Override
            public void onSuccess(Method method, GetResult<PlatformDto> response) {
                Window.alert("Product " + response.getResult());

            }

            @Override
            public void onFailure(Method method, Throwable e) {
                DisplayMessageEvent.fire(LoginPresenter.this, new Message(messages.unableToContactServer(), MessageStyle.ERROR));

                logger.log(Level.SEVERE, "callServerLoginAction(): Server failed to process login call.", e);
                e.printStackTrace();

            }
        });
        //        dispatchAsync.execute(productService.getProducts(), new AsyncCallback<GetResults<ProductDto>>() {
        //            @Override
        //            public void onFailure(Throwable e) {
        //                DisplayMessageEvent.fire(LoginPresenter.this, new Message(messages.unableToContactServer(), MessageStyle.ERROR));
        //
        //                logger.log(Level.SEVERE, "callServerLoginAction(): Server failed to process login call.", e);
        //                e.printStackTrace();
        //            }
        //
        //            @Override
        //            public void onSuccess(GetResults<ProductDto> result) {
        //                Window.alert("Product " + result.getResults().isEmpty());
        //
        //            }
        //        });

        //        dispatchAsync.execute(sessionService.login(loginRequest), new AsyncCallback<LogInResult>() {
        //            @Override
        //            public void onFailure(Throwable e) {
        //                DisplayMessageEvent.fire(LoginPresenter.this, new Message(messages.unableToContactServer(), MessageStyle.ERROR));
        //
        //                logger.log(Level.SEVERE, "callServerLoginAction(): Server failed to process login call.", e);
        //                e.printStackTrace();
        //            }
        //
        //            @Override
        //            public void onSuccess(LogInResult result) {
        //                if (result.getCurrentUserDto().isLoggedIn()) {
        //                    setLoggedInCookie(result.getLoggedInCookie());
        //                }
        //
        //                if (result.getActionType() == ActionType.VIA_COOKIE) {
        //                    onLoginCallSucceededForCookie(result.getCurrentUserDto());
        //                } else {
        //                    onLoginCallSucceeded(result.getCurrentUserDto());
        //                }
        //            }
        //        });
    }

    private void onLoginCallSucceededForCookie(CurrentUserDto currentUserDto) {
        getView().setLoginButtonEnabled(true);

        if (currentUserDto.isLoggedIn()) {
            onLoginCallSucceeded(currentUserDto);
        }
    }

    private void onLoginCallSucceeded(CurrentUserDto currentUserDto) {
        if (currentUserDto.isLoggedIn()) {
            currentUser.fromCurrentUserDto(currentUserDto);

            PlaceRequest homePlaceRequest = new Builder().nameToken(NameTokens.getOnLoginDefaultPage()).build();
            placeManager.revealPlace(homePlaceRequest);

            UserLoginEvent.fire(this);
            DisplayMessageEvent.fire(this, new Message(messages.onSuccessfulLogin(), MessageStyle.SUCCESS));
        } else {
            DisplayMessageEvent.fire(this, new Message(messages.invalidEmailOrPassword(), MessageStyle.ERROR));
        }
    }

    private void setLoggedInCookie(String value) {
        Cookies.removeCookie(LOGIN_COOKIE_NAME);

        Date expires = new Date();
        CalendarUtil.addDaysToDate(expires, 14);
        String domain = getDomain();
        String path = "/";
        boolean secure = false;
        Cookies.setCookie(LOGIN_COOKIE_NAME, value, expires, domain, path, secure);

        logger.info("LoginPresenter.setLoggedInCookie() Set client cookie=" + value);
    }

    private String getDomain() {
        String domain = GWT.getHostPageBaseURL();
        domain = domain.replaceAll(".*//", "");
        domain = domain.replaceAll("/", "");
        domain = domain.replaceAll(":.*", "");

        return domain;
    }

    private void tryLoggingInWithCookieFirst() {
        getView().setLoginButtonEnabled(false);
        LogInRequest loginRequest = new LogInRequest(getLoggedInCookie());
        callServerLoginAction(loginRequest);
    }

    private String getLoggedInCookie() {
        return Cookies.getCookie(LOGIN_COOKIE_NAME);
    }
}
