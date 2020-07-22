package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Category;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CategoryTest {
    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        db = MySQL.getInstance();
        Migration.createDatabase(db);
        Migration.createTables(db);
    }

    @Test
    void get() throws SQLException {
        // Save To DB
        Category category = new Category("testCategory");
        category.save();
        int saved_id = category.getId();
        Category category1 = Category.get(saved_id);

        assertEquals(category1.getId(), saved_id);
        assertEquals(category.getName(), category1.getName());

    }

    @Test
    void getAll() throws SQLException {
        // Initialize all users
        List<Category> allCategories = new ArrayList<Category>() {
            {
                add(new Category("axalikategoria1"));
                add(new Category("axalikategoria2"));
                add(new Category("axalikategoria3"));
                add(new Category("axalikategoria4"));

            }
        };
        // Save all of them in DB
        for (Category category : allCategories)
            category.save();

        List<Category> allCategoriesDB = Category.getAll();
        for (int i = 0; i < allCategoriesDB.size(); i++) {
            Category categoryDB = allCategoriesDB.get(i);
            Category category = allCategoriesDB.get(i);
            assertEquals(categoryDB.getName(), category.getName());
        }
    }

    @Test
    void update() throws SQLException {
        // Save To DB
        Category category = new Category("axalikategoria");
        category.save();
        int saved_id = category.getId();

        // Update user
        category.setName("axalikategoria2");

        // Should fail
        Category newCategory = Category.get(saved_id);
        assertEquals(newCategory.getId(), saved_id);
        assertNotEquals(newCategory.getName(), category.getName());

        // Should Pass after updating
        category.update();
        newCategory = Category.get(saved_id);
        assertEquals(newCategory.getId(), saved_id);
        assertEquals(newCategory.getName(), category.getName());
    }
}