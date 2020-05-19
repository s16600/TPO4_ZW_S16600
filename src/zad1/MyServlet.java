package zad1;
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

@WebServlet("/SelectBook")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyServlet() {
    		super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.sendRedirect("MainPage.jsp");
		
		PrintWriter out = response.getWriter();
		
		String text = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<form method=\"post\" action=\"SelectBook\">\r\n" + 
				"		<table>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>Wyszukaj tekst</td>\r\n" + 
				"				<td><input type=\"text\" name=\"aname\"></td>\r\n" + 
				"				<td><input type=\"submit\" name=\"button\"></td>\r\n" + 
				"			</tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		 
		out.println(text+"<br />"+"<br />");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname=request.getParameter("aname");
		PrintWriter out = response.getWriter();
		out.println("Wyszukiwany tekst: "+aname + "<br />" + "<br />");

	}
	
	
	public String bookList(String string) {
		//return "Lista książek"; //USUNAC !!!!!!
		
		String text = null;
		if (string==null) {
			Connection con = myConnection();
			try {
				Statement statement = con.createStatement();
				
				ResultSet resultSet = statement.executeQuery("SELECT author_surname, author_name, book_title, book_year "
						+ "FROM book INNER JOIN author ON book_author_id=author_id "
						+ "ORDER BY author_name DESC, book_year;");
				
				while (resultSet.next()){
					text+=(resultSet.getString(1)+" ");
					text+=(resultSet.getString(2)+", ");
					text+=(resultSet.getString(3)+", ");
					text+=(resultSet.getString(4)+".");
					text+=("<br />");
				}
				
			} catch (SQLException e) {
				text=("Brak połączenia z bazą danych");
			}
			
		} else {
			Connection con = myConnection();
			try {
				Statement statement = con.createStatement();
				
				ResultSet resultSet = statement.executeQuery("SELECT author_surname, author_name, book_title, book_year "
						+ "FROM book INNER JOIN author ON book_author_id=author_id "
						+ "WHERE ((book_title LIKE '%"+string+"%')OR(author_surname LIKE '%"+string+"%')) "
						+ "ORDER BY author_name DESC, book_year;");
				
				while (resultSet.next()){
					text+=(resultSet.getString(1)+" ");
					text+=(resultSet.getString(2)+", ");
					text+=(resultSet.getString(3)+", ");
					text+=(resultSet.getString(4)+".");
					text+=("<br />");
				}
			} catch (SQLException e) {
				text=("Brak połączenia z bazą danych");
			}
		}
		
		return text;
	}
	
	
	public String bookList() {
		return bookList(null);
	}
	
	
	public static Connection myConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver found.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver not found: "+e);
		}
		
		String url="jdbc:mysql://localhost/s16600?useSSL=false";
		String user="test";
		String password="haslo";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			//System.out.println("Connected.");
		} catch (SQLException e) {
			System.out.println("Not connected.");
		}
		return con;
	}

}
