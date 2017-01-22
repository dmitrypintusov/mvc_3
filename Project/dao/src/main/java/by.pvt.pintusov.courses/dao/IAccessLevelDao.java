package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.dao.IDao;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.AccessLevel;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 22.01.2017.
 */
public interface IAccessLevelDao extends IDao <AccessLevel> {

	AccessLevel getByAccessLevelType(AccessLevelType accessLevelType) throws DaoException;
}
