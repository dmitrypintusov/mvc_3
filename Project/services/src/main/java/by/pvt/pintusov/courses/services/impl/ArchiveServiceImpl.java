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
	private ArchiveDaoImpl markDao = ArchiveDaoImpl.getInstance();

	public static synchronized ArchiveServiceImpl getInstance () {
		if (instance == null) {
			instance = new ArchiveServiceImpl();
		}
		return instance;
	}

	private ArchiveServiceImpl () {
		super (Archive.class, ArchiveDaoImpl.getInstance());
	}
}
