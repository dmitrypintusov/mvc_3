package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.ArchiveDaoImpl;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IArchiveService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class ArchiveServiceImpl extends AbstractService<Archive> implements IArchiveService {
	private static Logger logger = Logger.getLogger(ArchiveServiceImpl.class);
	private static ArchiveServiceImpl instance;
	private SessionFactory sessionFactory = util.getSessionFactory();
	private ArchiveDaoImpl markDao = ArchiveDaoImpl.getInstance(sessionFactory);

	public static synchronized ArchiveServiceImpl getInstance (SessionFactory sessionFactory) {
		if (instance == null) {
			instance = new ArchiveServiceImpl(sessionFactory);
		}
		return instance;
	}

	private ArchiveServiceImpl (SessionFactory sessionFactory) {
		super (Archive.class, ArchiveDaoImpl.getInstance(sessionFactory), sessionFactory);
	}
}
