package by.pvt.pintusov.courses.commands.impl.user;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Registration command
 * @author pintusov
 * @version 1.1
 */

public class RegistrationCommand extends AbstractCommand {
	private User user;

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		try {
			user = RequestParameterParser.getUser(request);
			if (areFieldsFullStocked()) {
				if(UserServiceImpl.getInstance().checkIsNewUser(user)){
					UserServiceImpl.getInstance().bookUser(user);
					page = PagePathManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
					request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
				} else {
					page = PagePathManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
					request.setAttribute(Parameters.ERROR_USER_EXISTS, MessageManager.getInstance().getProperty(MessageConstants.USER_EXISTS));
				}
			} else {
				request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_FIELDS));
				page = PagePathManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
			}
		} catch (ServiceException e) {
			page = PagePathManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
			request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
		} catch (NumberFormatException e) {
			request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
			page = PagePathManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
		}
		catch (NullPointerException e) {
			page = PagePathManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
		}
		return page;
	}

	private boolean areFieldsFullStocked () {
		boolean isFullStocked = false;
		if (!user.getFirstName().isEmpty()
			& !user.getLastName().isEmpty()
			& !user.getLogin().isEmpty()
			& !user.getPassword().isEmpty()) {
			isFullStocked = true;
		}
		return isFullStocked;
	}
}
