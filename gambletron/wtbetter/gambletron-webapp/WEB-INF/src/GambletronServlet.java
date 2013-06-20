import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import javax.servlet.*;

public class GambletronServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException,
			IOException {
		PrintWriter out = res.getWriter();
		out.println( "Hello, Brave new World!" );
		out.close();
	}

}
