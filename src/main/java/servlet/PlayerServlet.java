package servlet;

import dao.PlayerDAO;
import entity.Player;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {

    PlayerDAO dao = new PlayerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect("player");
            return;
        }

        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Player p = dao.getById(id);
            req.setAttribute("player", p);
        }

        List<Player> list = dao.getAll();
        req.setAttribute("list", list);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");

        String name = req.getParameter("name");
        String full = req.getParameter("fullName");
        String age = req.getParameter("age");
        int indexId = Integer.parseInt(req.getParameter("indexId"));

        // VALIDATE
        if (name.isEmpty() || full.isEmpty()) {
            req.setAttribute("error", "Không được để trống");
            doGet(req, resp);
            return;
        }

        Player p = new Player();
        p.setName(name);
        p.setFullName(full);
        p.setAge(age);
        p.setIndexId(indexId);

        if (idStr == null || idStr.isEmpty()) {
            dao.insert(p);
        } else {
            p.setId(Integer.parseInt(idStr));
            dao.update(p);
        }

        resp.sendRedirect("player");
    }
}