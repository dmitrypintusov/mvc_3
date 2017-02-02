package by.pvt.pintusov.courses.commands.impl.teacher;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.services.impl.CourseServiceImpl;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 01.02.2017.
 */
public class ShowCoursesListCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		defineParameters(request);
		AccessLevelType accessLevelType = RequestParameterParser.getAccessLevelType(request);
		if(accessLevelType == AccessLevelType.TEACHER){
			try{
				int numberOfPages = CourseServiceImpl.getInstance().getNumberOfPages(recordsPerPage);
				List<Course> list = CourseServiceImpl.getInstance().getAllToPage(recordsPerPage, currentPage, ordering);
				session.setAttribute(Parameters.OPERATIONS_LIST, list);
				session.setAttribute(Parameters.NUMBER_OF_PAGES, numberOfPages);
				session.setAttribute(Parameters.CURRENT_PAGE, currentPage);
				session.setAttribute(Parameters.RECORDS_PER_PAGE, recordsPerPage);
				session.setAttribute(Parameters.ORDERING, ordering);
				page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_SHOW_OPERATIONS_PAGE);
			}
			catch (ServiceException e) {
				page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
				request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
			}
		}
		else{
			page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			session.invalidate();
		}
		return page;
	}

	// TODO вынести в RequestParameterParser
	private void defineParameters(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(request.getParameter(Parameters.ORDERING) != null){
			String parameter = request.getParameter(Parameters.ORDERING);
			switch (parameter){
				case "description":
					ordering = ORDER_BY_DESCRIPTION;
					break;
				case "amount":
					ordering = ORDER_BY_AMOUNT;
					break;
				case "client":
					ordering = ORDER_BY_CLIENT;
					break;
				case "account":
					ordering = ORDER_BY_ACCOUNT;
					break;
				default:
					ordering = ORDER_BY_DATE;
			}
			recordsPerPage = (Integer)session.getAttribute(Parameters.RECORDS_PER_PAGE);
		}
		else{
			ordering = ORDER_BY_DATE;
		}
		// TODO юрать параметр из сессии
		if(request.getParameter(Parameters.RECORDS_PER_PAGE) != null){
			recordsPerPage = Integer.valueOf(request.getParameter(Parameters.RECORDS_PER_PAGE));
			ordering = (String) session.getAttribute(Parameters.ORDERING);
		}
		else {
			recordsPerPage = 3;
		}
		if(request.getParameter(Parameters.CURRENT_PAGE) != null) {
			currentPage = Integer.parseInt(request.getParameter(Parameters.CURRENT_PAGE));
			recordsPerPage = (Integer) session.getAttribute(Parameters.RECORDS_PER_PAGE);
			ordering = (String) session.getAttribute(Parameters.ORDERING);
		}
		else{
			currentPage = 1;
		}
	}
}
