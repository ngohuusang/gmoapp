package vn.gmostore.basic.dto;

import vn.gmostore.basic.model.User;

public class CurrentUserDto implements Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Boolean loggedIn;
    User user;

    protected CurrentUserDto() {
    }

    public CurrentUserDto(Boolean loggedIn, User user) {
        this.loggedIn = loggedIn;
        this.user = user;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        String s = " { CurrentUserDto ";
        s += "loggedIn=" + loggedIn + " ";
        s += "user=" + user + " ";
        s += " CurrentUserDto }";
        return s;
    }
}
