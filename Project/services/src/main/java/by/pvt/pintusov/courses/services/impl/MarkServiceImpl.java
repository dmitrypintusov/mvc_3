package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.IMarkDao;
import by.pvt.pintusov.courses.dao.Impl.MarkDaoImpl;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.Mark;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IMarkService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
@Service
@Transactional (propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
public class MarkServiceImpl extends AbstractService<Mark> implements IMarkService {

	@Autowired
	public MarkServiceImpl (IMarkDao markDao) {
		super (markDao);
	}
}
