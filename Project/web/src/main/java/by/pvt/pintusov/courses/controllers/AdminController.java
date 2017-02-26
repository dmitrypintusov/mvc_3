package by.pvt.pintusov.courses.controllers;

import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.filters.PaginationFilter;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.IArchiveService;
import by.pvt.pintusov.courses.services.ICourseService;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.PrincipalUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin")
@SessionAttributes("filter")
public class AdminController {
	private static Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IArchiveService archiveService;
	@Autowired
	private PagePathManager pagePathManager;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PrincipalUtil principalUtil;

	@ModelAttribute("filter")
	public PaginationFilter createFilter() {
		return new PaginationFilter();
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showAdminMainPage (ModelMap modelMap) {
		modelMap.addAttribute(Parameters.USER, principalUtil.getPrincipal());
		return pagePathManager.getProperty(PagePath.ADMIN_PAGE_PATH);
	}

	@RequestMapping (value = "/maketeacher", method = GET)
	public String endCoursePage () {
		return pagePathManager.getProperty(PagePath.ADMIN_MAKE_TEACHER_PATH);
	}

	@RequestMapping (value = "/maketeacher", method = POST)
	public String makeTeacher (ModelMap modelMap,
	                         @RequestParam(value = Parameters.USER_LOGIN, required = false)String login,
	                         Locale locale) {
		String pagePath;
			try {
				User user = userService.getUserByLogin(login);
				AccessLevelType accessLevelType = AccessLevelType.TEACHER;
				AccessLevel accessLevel = new AccessLevel();
				accessLevel.setAccessLevelType(accessLevelType);
				user.addAccessLevel(accessLevel);
				userService.saveOrUpdate(user);
				modelMap.addAttribute(Parameters.SUCCESS_OPERATION, messageSource.getMessage("message.successoperation", null, locale));
				pagePath = pagePathManager.getProperty(PagePath.ADMIN_MAKE_TEACHER_PATH);
		} catch (ServiceException e) {
			modelMap.addAttribute(Parameters.ERROR_DATABASE, messageSource.getMessage("message.databaseerror", null, locale));
			pagePath = pagePathManager.getProperty(PagePath.ERROR_PAGE_PATH);
		}
		return pagePath;
	}

	@RequestMapping (value = "/archive", method = GET)
	public String archiveCoursePage () {
		return pagePathManager.getProperty(PagePath.ADMIN_ARCHIVE_COURSE_PATH);
	}

	@RequestMapping (value = "/archive", method = POST)
	public String archiveCourse (ModelMap modelMap,
	                         @RequestParam(value = Parameters.COURSE_ARCHIVE, required = false)String archiveCourse,
	                         Locale locale) {
		String pagePath;
		try {
			if (courseService.isCourseStatusEnded(archiveCourse)) {
				Course course = courseService.getByCourseName(archiveCourse);
				CourseStatusType courseStatus = CourseStatusType.ARCHIVE;
				Archive archive = new Archive();
				archive.addCourseToArchive(course);
				logger.info(archive);
				logger.info(course);
				archiveService.saveOrUpdate(archive);
				courseService.updateCourseStatus(course.getCourseName(), courseStatus);
				modelMap.addAttribute(Parameters.SUCCESS_OPERATION, messageSource.getMessage("message.successoperation", null, locale));
				pagePath = pagePathManager.getProperty(PagePath.ADMIN_ARCHIVE_COURSE_PATH);
			} else {
				modelMap.addAttribute(Parameters.COURSE_STATUS_OPEN, messageSource.getMessage("message.courseopen", null, locale));
				pagePath = pagePathManager.getProperty(PagePath.ADMIN_ARCHIVE_COURSE_PATH);
			}
		} catch (ServiceException e) {
			modelMap.addAttribute(Parameters.ERROR_DATABASE, messageSource.getMessage("message.databaseerror", null, locale));
			pagePath = pagePathManager.getProperty(PagePath.ERROR_PAGE_PATH);
		}
		return pagePath;
	}
}
