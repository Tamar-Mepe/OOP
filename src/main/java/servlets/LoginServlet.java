package servlets;

import models.User;
import utils.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> users = User.getAll();
        for (User user : users) {

            if (user.getUsername().equals(username)) {
                //TODO: String hashedPassword;
                if (BCrypt.checkpw(password, user.getPassword())) {
                    request.getSession().setAttribute(User.ATTRIBUTE_NAME,user.getId());
                    //TODO: move to front page
                } else {
                    //TODO: move to error page (wrong password)
                }
            }
        }

        //TODO: alert("incorrect username");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
