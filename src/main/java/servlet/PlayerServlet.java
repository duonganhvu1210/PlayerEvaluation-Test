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

        List<Player> list = dao.getAll();
        req.setAttribute("list", list);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String full = req.getParameter("fullName");
        String age = req.getParameter("age");
        int indexId = Integer.parseInt(req.getParameter("indexId"));

        // VALIDATE
        if(name.isEmpty() || full.isEmpty()){
            req.setAttribute("error","Không được để trống");
            doGet(req, resp);
            return;
        }

        Player p = new Player();
        p.setName(name);
        p.setFullName(full);
        p.setAge(age);
        p.setIndexId(indexId);

        dao.insert(p);

        resp.sendRedirect("player");
    }
}