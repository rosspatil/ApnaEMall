package com.binarysoftware.burnaby;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext  context=config.getServletContext();
		context.setAttribute("URL", "http://localhost:8080/burnaby/ControllerServlet");
		context.setAttribute("image", "D:\\SEED_JAVASOFT\\JSE_workspace\\burnaby\\burnaby\\images\\");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String base ="" ; 
		String url = "Default.jsp"; 
		String action = request.getParameter("action"); 

		if (action!=null) {
			if (action.equals("search")) 
				url = base + "SearchResult.jsp"; 
			else if (action.equals("browseCatalog")) 
				url = base + "BrowseCatalog.jsp"; 
			else if (action.equals("productDetails")) 
				url = base + "ProductDetails.jsp"; 
			else if (action.equals("addShoppingItem") || 
					action.equals("updateShoppingItem") || 
					action.equals("deleteShoppingItem") || 
					action.equals("displayShoppingCart")) 
				url = base + "ShoppingCart.jsp"; 
			else if (action.equals("checkOut")) 
				url = base + "CheckOut.jsp"; 
			else if (action.equals("order")) 
				url = base + "Order.jsp"; 
			else if(action.equals("login"))
				url=base+"login.jsp";
			else if(action.equals("loggedin")){
				String user=	request.getParameter("login");
				String pswd=	request.getParameter("pswd");			
				 if(isValid(user, pswd)){
					
					 url=base+"Default.jsp";
				}			
				else{
					response.setHeader("Refresh", "5; URL=" + "login.jsp");			
					PrintWriter out = response.getWriter();
					out.println("Login is incorrect...");
					out.println("Your will be taken back to Login page in 5 seconds.");
					out.flush();
					out.close();

				}				
			} 
		}
		RequestDispatcher requestDispatcher =request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response); 

	} 

	boolean isValid(String user,String pswd) {
		boolean valid=false;
		PreparedStatement st;
		try {
			st = Database.getConnection().prepareStatement("select * from user where login=? and password=?;");
			st.setString(1, user);
			st.setString(2, pswd);
			st.execute();
			ResultSet rs=st.getResultSet();
			while(rs.next()){
				if(user.equals((rs.getString("login")))&& pswd.equals((rs.getString("password")))){
					valid=true;
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valid;
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
