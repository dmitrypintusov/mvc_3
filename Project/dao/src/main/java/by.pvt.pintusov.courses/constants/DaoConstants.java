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
	public static final String ERROR_USERS_LIST = "Unable to return list of users. Error was thrown in DAO: ";

	public static final String PARAMETER_ID = "id";
	public static final String PARAMETER_USER_LOGIN = "login";
	public static final String PARAMETER_USER_PASSWORD = "password";
	public static final String PARAMETER_COURSE_STATUS = "courseStatus";
	public static final String PARAMETER_ACCESS_LEVEL_TYPE = "accessLevelType";

	public static final String HQL_GET_BY_LOGIN = "FROM User U WHERE U.login =:login";
	public static final String HQL_CHECK_AUTHORIZATION = "FROM User U WHERE U.login = :login AND U.password = :password";
	public static final String HQL_GET_BY_ACCESS_LEVEL = "FROM AccessLevel A WHERE A.accessLevelType = :accessLevelType";
	public static final String HQL_GET_ALL_USERS = " from User";
}
