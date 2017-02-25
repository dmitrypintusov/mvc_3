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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Project: courses
 * Created by: USER
 * Date: 17.02.17.
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
	private static Logger logger = Logger.getLogger(TeacherController.class);

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

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showTeacherMainPage (ModelMap modelMap) {
		modelMap.addAttribute(Parameters.USER, principalUtil.getPrincipal());
		return pagePathManager.getProperty(PagePath.TEACHER_PAGE_PATH);
	}

	@RequestMapping(value = "/addcourse", method = GET)
	public String showAddCoursePage(ModelMap modelMap){
		modelMap.addAttribute(Parameters.USER, principalUtil.getPrincipal());
		return pagePathManager.getProperty(PagePath.TEACHER_ADD_COURSE_PATH);
	}

	@RequestMapping(value = "/addcourse", method = POST)
	public String addCourse (ModelMap modelMap,
	                       Course course,
	                       BindingResult bindingResult,
	                       Locale locale) {
		String pagePath;
			if(!bindingResult.hasErrors()) {
				try {
					courseService.startCourse(course);
					modelMap.addAttribute(Parameters.SUCCESS_OPERATION, messageSource.getMessage("message.successoperation", null, locale));
					pagePath = pagePathManager.getProperty(PagePath.TEACHER_ADD_COURSE_PATH);
				} catch (ServiceException e) {
					modelMap.addAttribute(Parameters.ERROR_DATABASE, messageSource.getMessage("message.databaseerror",null, locale));
					pagePath = pagePathManager.getProperty(PagePath.ERROR_PAGE_PATH);
				} catch (NullPointerException e) {
					logger.error("Error in Teacher Controller add course method: ", e);
					pagePath = pagePathManager.getProperty(PagePath.ERROR_PAGE_PATH);
				}
			} else {
				pagePath = pagePathManager.getProperty(PagePath.REGISTRATION_PAGE_PATH);
			}
		return pagePath;
	}
}
