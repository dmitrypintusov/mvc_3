package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.IArchiveDao;
import by.pvt.pintusov.courses.pojos.Archive;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Project: courses
 * Created by: USER
 * Date: 23.01.17.
 */
public class ArchiveDaoImpl extends AbstractDao<Archive> implements IArchiveDao {
	private static Logger logger = Logger.getLogger(ArchiveDaoImpl.class);
	private static ArchiveDaoImpl instance;

	private ArchiveDaoImpl(SessionFactory sessionFactory){
		super(Archive.class, sessionFactory);
	}

	public static synchronized ArchiveDaoImpl getInstance(SessionFactory sessionFactory){
		if(instance == null){
			instance = new ArchiveDaoImpl(sessionFactory);
		}
		return instance;
	}
}
