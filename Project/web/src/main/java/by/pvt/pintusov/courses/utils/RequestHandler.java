package by.pvt.pintusov.courses.utils;

import by.pvt.pintusov.courses.commands.ICommand;
import by.pvt.pintusov.courses.commands.factory.CommandFactory;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.managers.PagePathManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request Handler
 * @author pintusov
 * @version 1.1
 */

public class RequestHandler {
	private RequestHandler () {}

	/**
	 * Process request using Command factory
	 * @param request - request from server
	 * @param response - response to server
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandFactory commandFactory = CommandFactory.getInstance();
		ICommand сommand = commandFactory.defineCommand(request);
		String page = сommand.execute(request);
		if (page != null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			page = PagePathManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			response.sendRedirect(request.getContextPath() + page);
		}
	}
}