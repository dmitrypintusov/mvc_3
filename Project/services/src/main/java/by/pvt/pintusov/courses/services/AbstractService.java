package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.entities.Entity;
import by.pvt.pintusov.courses.managers.HikariCP;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;

import java.sql.Connection;

/**
 * Service abstract class implements IService
 * @author pintusov
 * @version 1.0
 */

abstract public class AbstractService <T extends  Entity> implements IService <T> {
	protected Connection connection;
	protected HikariCP manager = HikariCP.getInstance();
	protected CoursesSystemLogger logger = CoursesSystemLogger.getInstance();
}
