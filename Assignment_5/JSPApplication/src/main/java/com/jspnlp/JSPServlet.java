package com.jspnlp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/helloJSP")
public class JSPServlet extends HttpServlet {
    public JSPServlet(){
        super();
    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("yourName");
        PrintWriter writer = response.getWriter();
        writer.println("<h1> Hello " + name + " Welcome to the Web Page !" + "</h1>");
        writer.close();
    }
}
