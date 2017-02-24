package by.pvt.pintusov.courses.controllers;

import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.services.ICourseService;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.PrincipalUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Project: courses
 * Created by: USER
 * Date: 17.02.17.
 */

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	private static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private PagePathManager pagePathManager;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PrincipalUtil principalUtil;

	@RequestMapping (value = "/main", method = GET)
	public String showStudentMainPage (ModelMap modelMap) {
		modelMap.addAttribute(Parameters.USER, principalUtil.getPrincipal());
		return pagePathManager.getProperty(PagePath.STUDENT_PAGE_PATH);
	}

	@RequestMapping(value = "/attendcourse", method = GET)
	public String showAttendCoursePage (ModelMap modelMap) {
		modelMap.addAttribute(Parameters.USER, principalUtil.getPrincipal());
		return pagePathManager.getProperty(PagePath.STUDENT_ATTEND_COURSE_PATH);
	}

	@RequestMapping(value = "/attendcourse", method = POST)
	public String attendCourse (ModelMap modelMap,
	                            Course course,
	                            Locale locale) {
		String pagePath;
		try {
			List<Course> coursesList = courseService.getAll();
			logger.info(coursesList);
			if (coursesList.isEmpty()) {
				modelMap.addAttribute(Parameters.ERROR_EMPTY_LIST, messageSource.getMessage("message.emptylist", null, locale));
			} else {
				modelMap.addAttribute(Parameters.COURSES_LIST, coursesList);
			}
			pagePath = pagePathManager.getProperty(PagePath.STUDENT_ATTEND_COURSE_PATH);
		} catch (ServiceException e) {
			modelMap.addAttribute(Parameters.ERROR_DATABASE, messageSource.getMessage("message.databaseerror", null, locale));
			pagePath = pagePathManager.getProperty(PagePath.ERROR_PAGE_PATH);
		}
		return pagePath;
	}
}
