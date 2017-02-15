package by.pvt.pintusov.courses.commands.impl.teacher;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.MessageConstants;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.managers.MessageManager;
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
	private int currentPage;
	private int recordsPerPage;
	private String ordering;

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		RequestParameterParser.defineParameters(request);
		AccessLevelType accessLevelType = RequestParameterParser.getAccessLevelType(request);
		if(accessLevelType == AccessLevelType.TEACHER){
			try{
				int numberOfPages = CourseServiceImpl.getInstance().getNumberOfPages(recordsPerPage);
				List<Course> list = CourseServiceImpl.getInstance().getAllToPage(recordsPerPage, currentPage, ordering);
				session.setAttribute(Parameters.COURSES_LIST, list);
				session.setAttribute(Parameters.NUMBER_OF_PAGES, numberOfPages);
				session.setAttribute(Parameters.CURRENT_PAGE, currentPage);
				session.setAttribute(Parameters.RECORDS_PER_PAGE, recordsPerPage);
				session.setAttribute(Parameters.ORDERING, ordering);
				page = PagePathManager.getInstance().getProperty(PagePath.TEACHER_COURSES_PAGE);
			}
			catch (ServiceException e) {
				page = PagePathManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
				request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
			}
		}
		else{
			page = PagePathManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			session.invalidate();
		}
		return page;
	}
}
