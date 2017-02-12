package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.AccessLevel;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public interface IAccessLevelService extends IService<AccessLevel> {

	AccessLevel getByAccessLevelType(AccessLevelType accessLevelType) throws ServiceException;
}
