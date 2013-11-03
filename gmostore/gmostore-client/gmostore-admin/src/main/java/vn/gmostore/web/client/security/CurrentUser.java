package vn.gmostore.web.client.security;

import vn.gmostore.basic.dto.CurrentUserDto;
import vn.gmostore.basic.model.User;

public class CurrentUser {
    private Boolean loggedIn;
    private User user;

    public CurrentUser() {
        loggedIn = false;
    }

    public void fromCurrentUserDto(CurrentUserDto currentUserDto) {
        setLoggedIn(true);
        setUser(currentUserDto.getUser());
    }

    public void reset() {
        setLoggedIn(false);
        setUser(null);
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
