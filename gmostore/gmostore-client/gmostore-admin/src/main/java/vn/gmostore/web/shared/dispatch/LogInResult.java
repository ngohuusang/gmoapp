package vn.gmostore.web.shared.dispatch;

import vn.gmostore.basic.dto.ActionType;
import vn.gmostore.basic.dto.CurrentUserDto;

import com.gwtplatform.dispatch.shared.Result;

public class LogInResult implements Result {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    ActionType actionType;
    CurrentUserDto currentUserDto;
    String loggedInCookie;

    protected LogInResult() {
        // Needed for serialization
    }

    public LogInResult(ActionType actionType, CurrentUserDto currentUserDto, String loggedInCookie) {
        this.actionType = actionType;
        this.currentUserDto = currentUserDto;
        this.loggedInCookie = loggedInCookie;
    }

    public CurrentUserDto getCurrentUserDto() {
        return currentUserDto;
    }

    public String getLoggedInCookie() {
        return loggedInCookie;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
