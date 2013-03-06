package com.savin.task7;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SimpleServlet",urlPatterns={"/simple"})
public class Hello extends HttpServlet {

    @Resource(name = "jdbc/testDC")
    private DataSource ds;

    private GuestBook guestBook;
    @PostConstruct
    public void initGuestBook(){
        guestBook=new GuestBook(ds);
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void doGet(HttpServletRequest req,HttpServletResponse response) throws ServletException,IOException {
        try {
            req.setAttribute("GuestList",guestBook.list());
            req.getRequestDispatcher("WEB-INF/test.jsp").forward(req, response);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse response) throws ServletException,IOException{
        try {
            guestBook.add(req.getParameter("Rollno"));
            req.setAttribute("GuestList",guestBook.list());
            req.setAttribute("GuestListSize",guestBook.list().size());
            req.getRequestDispatcher("WEB-INF/test.jsp").forward(req, response);

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
