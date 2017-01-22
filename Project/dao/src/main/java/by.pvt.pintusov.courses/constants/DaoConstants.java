package by.pvt.pintusov.courses.constants;

/**
 * Project: courses
 * Created by: USER
 * Date: 20.01.17.
 */
public class DaoConstants {
	public static final String ERROR_DAO = "Error was thrown in DAO: ";
	public static final String ERROR_USER_BY_LOGIN = "Unable to return user by login. Error was thrown in DAO: ";
	public static final String ERROR_AUTHORIZATION = "Unable to check authorization. Error was thrown in DAO: ";

	public static final String PARAMETER_USER_LOGIN = "login";
	public static final String PARAMETER_USER_PASSWORD = "password";

	public static final String HQL_GET_BY_LOGIN = "from User where login = :login";
	public static final String HQL_CHECK_AUTHORIZATION = "from User where login = :login and password = :password";

}
