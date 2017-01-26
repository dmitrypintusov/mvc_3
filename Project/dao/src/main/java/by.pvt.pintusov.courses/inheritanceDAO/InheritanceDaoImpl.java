package by.pvt.pintusov.courses.inheritanceDAO;

import by.pvt.pintusov.courses.enheritance.Person;
import org.apache.log4j.Logger;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 23.01.2017.
 */
public class InheritanceDaoImpl extends AbstractInheritanceDao<Person> {
	private static Logger logger = Logger.getLogger(InheritanceDaoImpl.class);
	private static InheritanceDaoImpl instance;

	public static synchronized InheritanceDaoImpl getInstance(){
		if(instance == null){
			instance = new InheritanceDaoImpl();
		}
		return instance;
	}
	private InheritanceDaoImpl(){
		super(Person.class);
	}

}
