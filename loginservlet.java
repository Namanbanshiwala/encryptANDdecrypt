package page;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginservlet
 */

@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //create table login(email VARCHAR(100) UNIQUE NOT NULL, password varchar(100));
        //insert into login values('naman@29','admin');
        //select * from login where email = 'naman@29' and password = 'admin';
        
      //JDBC CONNECTION
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_info", "root", "root");
			Statement st = conn.createStatement();
			
			String query = "select * from login where email = '"+username+"' and password = '"+password+"'";
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
                
                response.sendRedirect("main.html");
            } else {
                //the user id and password is not available in DB
                out.print("<br><br><h1>" + username + ": Please enter correct userId and Password</h1><br>");
                out.print("<h1>Login Failed...!</h1><br>");
            }
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.print("<h1>Login Failed...! because of server exception</h1><br>");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("<h1>Login Failed...! because of server exception</h1><br>");
			e.printStackTrace();
		}
        
        //out.print("username: "+username);
        //out.print("password: "+password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
