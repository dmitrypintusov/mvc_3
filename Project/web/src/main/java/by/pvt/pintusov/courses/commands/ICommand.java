package by.pvt.pintusov.courses.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface
 * @author pintusov
 * @version 1.1
 */

public interface ICommand {
	String execute (HttpServletRequest request);
}