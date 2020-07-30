package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReviewTest {

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    void get() {
        Review review = (Review) new Review("comment", -1, -2, -3).save();
        int savedId = review.getId();
        Review newReview = Review.get(savedId);
        assertEquals(review.getComment(), newReview.getComment());
        assertEquals(review.getPoints(), newReview.getPoints());
        assertEquals(review.getProductId(), newReview.getProductId());
        assertEquals(review.getUserId(), newReview.getUserId());

        review.setComment("new comment");
        review.setPoints(1);
        review.setUserId(2);
        review.setUserId(3);
        assertNotEquals("new comment", newReview.getComment());
        assertNotEquals(1, newReview.getPoints());
        assertNotEquals(2, newReview.getProductId());
        assertNotEquals(3, newReview.getUserId());
        review.update();
        newReview = Review.get(savedId);
        assertEquals(review.getComment(), newReview.getComment());
        assertEquals(review.getPoints(), newReview.getPoints());
        assertEquals(review.getProductId(), newReview.getProductId());
        assertEquals(review.getUserId(), newReview.getUserId());
    }

    @Test
    void getAll() {
        List<Review> allReviews = new ArrayList<Review>() {
            {
                add((Review) new Review("comment1", 1, 10, 1).save());
                add((Review) new Review("comment2", 2, 100, 2).save());
                add((Review) new Review("comment3", 3, 1000, 1).save());
                add((Review) new Review("comment4", 4, 10000, 3).save());
                add((Review) new Review("comment5", 5, 100000, 4).save());
            }
        };

        List<Review> AllReviewsDB = Review.getAll();
        for (int i = 0; i < AllReviewsDB.size(); i++) {
            Review reviewDB = AllReviewsDB.get(i);
            Review review = allReviews.get(i);
            assertEquals(reviewDB.getId(), review.getId());
            assertEquals(reviewDB.getProductId(), review.getProductId());
            assertEquals(reviewDB.getUserId(), review.getUserId());
            assertEquals(reviewDB.getComment(), review.getComment());
        }
    }

    @Test
    void getReviewsByProductId() {
        new Review("comment1", 1, 10, 1).save();
        new Review("comment2", 2, 100, 2).save();
        new Review("comment3", 3, 1000, 1).save();
        new Review("comment4", 4, 10000, 3).save();
        new Review("comment5", 4, 10000, 1).save();
        new Review("comment6", 5, 100000, 4).save();
        List<Review> productReviews = Review.getReviewsByProductId(1);
        assertEquals(3, productReviews.size());
        for (Review review : productReviews) {
            String comment = review.getComment();
            assertNotEquals("comment2", comment);
            assertNotEquals("comment2", comment);
            assertNotEquals("comment2", comment);
        }
    }
}