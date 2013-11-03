package vn.gmostore.web.client.place;

public class NameTokens {
    public static final String login = "!login";
    public static final String manufacturer = "!manufacturer";
    public static final String detailManufacturer = "!detailManufacturer";
    public static final String rating = "!rating";
    public static final String detailRating = "!detailRating";
    public static final String products = "!products";
    public static final String report = "!report";
    public static final String newProduct = "!newProduct";

    public static String getOnLoginDefaultPage() {
        return manufacturer;
    }

    public static String getManufacturer() {
        return manufacturer;
    }

    public static String getDetailManufacturer() {
        return detailManufacturer;
    }

    public static String getRating() {
        return rating;
    }

    public static String getDetailRating() {
        return detailRating;
    }

    public static String getProducts() {
        return products;
    }

    public static String getReport() {
        return report;
    }
}
