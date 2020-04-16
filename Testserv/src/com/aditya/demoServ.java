package com.aditya;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aditya.JDBCserv;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 

@WebServlet("/InsertData") 
public class demoServ extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		String name = req.getParameter("empname");
		String id = req.getParameter("empid");
		String age = req.getParameter("age");
		try {
			Connection con = JDBCserv.initializeDB();
			PreparedStatement st = con.prepareStatement("insert into employee value(?,?,?)");
			st.setString(1, name);
			st.setInt(2, Integer.valueOf(id));
			st.setInt(3, Integer.valueOf(age));
			st.executeUpdate();
			st.close();
			con.close();
			PrintWriter out = res.getWriter();
			out.println("<html><body><b>Successfully Inserted "+"</b></body></html>");
			out.println(" Hi "+name+" employee ID "+id+" of age "+age);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
