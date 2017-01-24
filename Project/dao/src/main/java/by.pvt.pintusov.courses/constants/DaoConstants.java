package by.pvt.pintusov.courses.constants;

/**
 * Project: courses
 * Created by: USER
 * Date: 20.01.17.
 */
public class DaoConstants {
	public static final String ERROR_DAO = "Error was thrown in DAO: ";
	public static final String ERROR_USER_BY_LOGIN = "Unable to return user by login. Error was thrown in DAO: ";
	public static final String ERROR_USER_AUTHORIZATION = "Unable to check authorization. Error was thrown in DAO: ";
	public static final String ERROR_COURSE_STATUS = "Unable to check course status. Error was thrown in DAO: ";
	public static final String ERROR_ACCESS_LEVEL_TYPE = "Unable to return access level type. Error was thrown in DAO: ";

	public static final String PARAMETER_ID = "id";
	public static final String PARAMETER_USER_LOGIN = "F_LOGIN";
	public static final String PARAMETER_USER_PASSWORD = "F_PASSWORD";
	public static final String PARAMETER_COURSE_STATUS = "F_COURSESTATUS";
	public static final String PARAMETER_ACCESS_LEVEL_TYPE = "F_ACCESSLEVELTYPE";

	public static final String HQL_GET_BY_LOGIN = "from User where F_LOGIN = :F_LOGIN";
	public static final String HQL_CHECK_AUTHORIZATION = "from User where F_LOGIN = :F_LOGIN and F_PASSWORD = :F_PASSWORD";
	public static final String HQL_GET_BY_ACCESS_LEVEL = "from AccessLevel where F_ACCESSLEVELTYPE = :F_ACCESSLEVELTYPE";
}
