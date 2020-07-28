package servlets;

import models.Cart;
import models.Product;
import models.User;
import utils.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        if(request.getParameter("quantity-select")!=null)
        {
            Integer userId = (Integer)request.getSession().getAttribute(User.ATTRIBUTE_NAME);
            if(userId == null){
                response.sendRedirect("/itempage.jsp?id=" + productId);
                return;
            }
            int quantity = Integer.parseInt(request.getParameter("quantity-select"));
            new Cart(userId, productId, quantity).save();
            Product prod = Product.get(productId);
            prod.setQuantity(prod.getQuantity() - quantity);
            prod.update();
        }
        response.sendRedirect("/itempage.jsp?id=" + productId);
        return;
    }
}