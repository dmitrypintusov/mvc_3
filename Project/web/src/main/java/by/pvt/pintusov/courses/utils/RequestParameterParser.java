package by.pvt.pintusov.courses.utils;

import by.pvt.pintusov.courses.commands.factory.CommandType;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.pojos.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Request parameter parser
 * @author pintusov
 * @version 1.1
 */

public class RequestParameterParser {

	private RequestParameterParser() {}

	/**
	 * Get command type
	 * @param request - used for getting attribute
	 * @return command type
	 */
	public static CommandType getCommandType(HttpServletRequest request) {
		String commandName = request.getParameter(Parameters.COMMAND);
		CommandType commandType = CommandType.LOGIN;
		if (commandName != null) {
			commandType = CommandType.valueOf(commandName.toUpperCase());
		}
		return commandType;
	}

	/**
	 * Get User
	 * @param request - used for creating instance
	 * @return user instance
	 */
	public static User getUser(HttpServletRequest request) {
		AccessLevel access = new AccessLevel();
		AccessLevelType accessLevel;
		Set<AccessLevel> accessLevels = new HashSet<>();
		if (request.getParameter(Parameters.USER_ACCESS_LEVEL) != null) {
			accessLevel = AccessLevelType.valueOf(request.getParameter(Parameters.USER_ACCESS_LEVEL).toUpperCase());
			access.setAccessLevelType(accessLevel);
			accessLevels.add(access);
		} else {
			accessLevel = AccessLevelType.STUDENT;
			access.setAccessLevelType(accessLevel);
			accessLevels.add(access);
		}
		String firstName = request.getParameter(Parameters.USER_FIRST_NAME);
		String lastName = request.getParameter(Parameters.USER_LAST_NAME);
		String login = request.getParameter(Parameters.USER_LOGIN);
		String password = request.getParameter(Parameters.USER_PASSWORD);

		User user = EntityBuilder.buildUser(firstName, lastName, login, password, null, null, accessLevels);
		return user;
	}

	/**
	 * Get User type
	 * @param request - used for getting attribute
	 * @return course instance
	 */
	public static AccessLevelType getAccessLevelType (HttpServletRequest request) {
		return (AccessLevelType) request.getSession().getAttribute(Parameters.USER_ACCESS_LEVEL);
	}

	/**
	 * Get record users
	 * @param request - used for getting attribute
	 * @return course instance
	 */
	public static User getRecordUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Parameters.USER);
	}

	public static void defineParameters(HttpServletRequest request) {
		int currentPage;
		int recordsPerPage;
		String ordering;
		HttpSession session = request.getSession();
		if(request.getParameter(Parameters.ORDERING) != null){
			String parameter = request.getParameter(Parameters.ORDERING);
			switch (parameter){
				case "courseName":
					ordering = Parameters.ORDER_BY_COURSE_NAME;
					break;
				case "courseStatus":
					ordering = Parameters.ORDER_BY_COURSE_STATUS;
					break;
				case "hours":
					ordering = Parameters.ORDER_BY_HOURS;
					break;
				case "startDate":
					ordering = Parameters.ORDER_BY_START_DATE;
					break;
				case "updateDate":
					ordering = Parameters.ORDER_BY_UPDATE_DATE;
				break;
				default:
					ordering = Parameters.ORDER_BY_ID;
			}
			recordsPerPage = (Integer)session.getAttribute(Parameters.RECORDS_PER_PAGE);
		}
		else{
			ordering = Parameters.ORDER_BY_ID;
		}

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
