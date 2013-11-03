package vn.gmostore.web.client.application.widget.header;

import vn.gmostore.web.client.place.NameTokens;

public enum MenuItem {
    PRODUCT("Products", NameTokens.products), //
    RATING("Ratings", NameTokens.rating), //
    REPORT("Reports", NameTokens.report);

    private String label;
    private String placeToken;

    private MenuItem(String label, String placeToken) {
        this.label = label;
        this.placeToken = placeToken;
    }

    public String getPlaceToken() {
        return placeToken;
    }

    @Override
    public String toString() {
        return label;
    }
}
