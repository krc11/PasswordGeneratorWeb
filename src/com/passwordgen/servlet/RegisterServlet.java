@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username, password);
        if (userDAO.registerUser(user)) {
            resp.sendRedirect("web/login.html?reg=success");
        } else {
            resp.sendRedirect("web/register.html?error=1");
        }
    }
}