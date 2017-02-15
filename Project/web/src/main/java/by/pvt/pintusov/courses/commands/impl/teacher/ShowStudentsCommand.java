package by.pvt.pintusov.courses.commands.impl.teacher;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 01.02.2017.
 */
public class ShowStudentsCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page;
		HttpSession session = request.getSession();
		AccessLevelType accessLevelType = RequestParameterParser.getAccessLevelType(request);
		if(accessLevelType == AccessLevelType.TEACHER){
			try{
				List<User> list = UserServiceImpl.getInstance().getAll();
				session.setAttribute(Parameters.USERS_LIST, list);
				page = PagePathManager.getInstance().getProperty(PagePath.TEACHER_SHOW_STUDENTS_PAGE);
			}
			catch (ServiceException e) {
				page = PagePathManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
				request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
			}
		} else{
			page = PagePathManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			session.invalidate();
		}
		return page;
	}
}
