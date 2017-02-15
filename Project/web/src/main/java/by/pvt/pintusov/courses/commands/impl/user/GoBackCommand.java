package by.pvt.pintusov.courses.commands.impl.user;

import by.pvt.pintusov.courses.commands.AbstractCommand;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.managers.PagePathManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to index page command
 * @author pintusov
 * @version 1.1
 */

public class GoBackCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = PagePathManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
		return page;
	}
}
