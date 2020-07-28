package servlets;

import models.Cart;
import models.Category;
import models.Product;
import models.User;
import utils.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet("/SellServlet")
@MultipartConfig
public class SellServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("product-name");
        String productDescription = request.getParameter("product-description");
        String productQuantityString  = request.getParameter("product-quantity");
        String productPriceString  = request.getParameter("product-price");
        String productCategory= request.getParameter("category-select");
        // for image parsing
        String imageAddress = "blablabla";
        String errorMessage = "";

        if (productName.equals("") || productDescription.equals("") || productQuantityString.equals("") || productPriceString.equals("") || imageAddress.equals("")) {

            errorMessage = "Please fill blank spaces";
            request.setAttribute("error", errorMessage);
            RequestDispatcher view = request.getRequestDispatcher("/sell.jsp");
            view.forward(request, response);
            return;
        }
        Integer productQuantity  = Integer.parseInt(productQuantityString);
        Double productPrice  = Double.parseDouble(productPriceString);

        // TODO: download and save image to images
        
        // add product and save it
        Integer userId = (Integer)request.getSession().getAttribute(User.ATTRIBUTE_NAME);
        User user = User.get(userId);  //shouldn't be null
        Category category = Category.getByName(productCategory);
        new Product(productName,productDescription,productPrice,category.getId(),productQuantity,user.getId(),imageAddress).save();

    }
}
