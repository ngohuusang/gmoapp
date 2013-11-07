package vn.gmostore.api.service;


public interface RatingService {

    boolean rate(Integer productId, String username, int mark);

}
