package by.pvt.pintusov.courses.utils;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * Project: courses
 * Created by: USER
 * Date: 21.01.17.
 */
public class CustomNamingStrategy extends DefaultNamingStrategy {
	@Override
	public String classToTableName(String className) {
		return "T_" + super.classToTableName(className).toUpperCase();
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return "F_" + super.propertyToColumnName(propertyName).toUpperCase();
	}
}
