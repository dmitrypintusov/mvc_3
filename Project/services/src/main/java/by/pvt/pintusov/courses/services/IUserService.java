package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */
public interface IUserService extends IService<User> {

	boolean checkUserAuthorization(String login, String password) throws ServiceException;
	User getUserByLogin(String login) throws ServiceException;
	AccessLevelType checkAccessLevel(User user) throws ServiceException;
	boolean checkIsNewUser(User user) throws ServiceException;
	void bookUser(User user) throws ServiceException;
}
