package vn.gmostore.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gmostore.api.service.RatingService;
import vn.gmostore.basic.model.Rating;
import vn.gmostore.server.manager.ProductManager;
import vn.gmostore.server.manager.RatingManager;
import vn.gmostore.server.manager.UserManager;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingManager ratingManager;

    @Autowired
    UserManager userManager;

    @Autowired
    ProductManager productManager;

    @Override
    public boolean rate(Integer productId, String username, int mark) {
        Rating rating = ratingManager.rate(productId, username, mark);

        return rating.isPersisted();
    }

    public boolean canRate(Integer productId, String username) {
        Rating rating = ratingManager.getBy(productId, username);

        return rating == null;
    }
}
