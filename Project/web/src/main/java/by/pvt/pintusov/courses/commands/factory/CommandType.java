package by.pvt.pintusov.courses.commands.factory;

import by.pvt.pintusov.courses.commands.ICommand;
import by.pvt.pintusov.courses.commands.impl.student.ShowTeachersCommand;
import by.pvt.pintusov.courses.commands.impl.teacher.GoBackTeacherCommand;
import by.pvt.pintusov.courses.commands.impl.teacher.ShowCoursesListCommand;
import by.pvt.pintusov.courses.commands.impl.teacher.ShowStudentsCommand;
import by.pvt.pintusov.courses.commands.impl.user.*;

/**
 * Command factory
 * @author pintusov
 * @version 1.1
 */

public enum CommandType {
	//users commands
	LOGIN, LOGOUT, REGISTRATION, GO_TO_REGISTRATION, BACK,

	//students commands
	SHOW_TEACHERS,

	//teachers commands
	COURSES_LIST, SHOW_STUDENTS, BACK_TEACHER;

	//TODO доделать
	/**
	 * Gets current command
	 * @return command depending on request
	 */
	public ICommand getCurrentCommand () throws EnumConstantNotPresentException {
		switch (this) {
			//user commands
			case LOGIN:
				return new LoginUserCommand();
			case LOGOUT:
				return new LogoutUserCommand();
			case REGISTRATION:
				return new RegistrationCommand();
			case GO_TO_REGISTRATION:
				return new GoToRegistrationCommand();
			case BACK:
				return new GoBackCommand();

			//teacher`s commands
			case BACK_TEACHER:
				return new GoBackTeacherCommand();
			case SHOW_STUDENTS:
				return new ShowStudentsCommand();
			case COURSES_LIST:
				return new ShowCoursesListCommand();

			//students commands
			case SHOW_TEACHERS:
				return new ShowTeachersCommand();


			default:
				return new LoginUserCommand();
		}
	}
}
