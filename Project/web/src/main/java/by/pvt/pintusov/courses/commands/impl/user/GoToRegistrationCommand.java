package by.pvt.pintusov.courses.commands.impl.user;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.managers.PagePathManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to registration command
 * @author pintusov
 * @version 1.1
 */

public class GoToRegistrationCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = PagePathManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
		return page;
	}
}
