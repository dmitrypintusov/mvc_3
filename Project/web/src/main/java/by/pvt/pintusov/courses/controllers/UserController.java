package by.pvt.pintusov.courses.controllers;

import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.constants.WebConstants;
import by.pvt.pintusov.courses.dto.UserDTO;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Project: courses
 * Created by: USER
 * Date: 15.02.17.
 */
@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private PagePathManager pagePathManager;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/home" , method = RequestMethod.GET)
	public String showHomePage(){
		return pagePathManager.getProperty(PagePath.HOME_PAGE_PATH);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUser () {
		return pagePathManager.getProperty(PagePath.HOME_PAGE_PATH);
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logoutUser (HttpServletRequest request,
	                          HttpServletResponse response,
	                          RedirectAttributes redirectAttributes,
	                          Locale locale) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equalsIgnoreCase(WebConstants.ANONYMOUS_USER)) {
			redirectAttributes.addFlashAttribute(Parameters.ERROR_LOGIN_OR_PASSWORD, messageSource.getMessage("message.loginerror", null, locale));
		}
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/" + pagePathManager.getProperty(PagePath.HOME_PAGE_PATH);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(ModelMap model){
		model.addAttribute(Parameters.NEW_USER, new UserDTO());
		return pagePathManager.getProperty(PagePath.REGISTRATION_PAGE_PATH);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(ModelMap modelMap,
	                           @ModelAttribute(Parameters.NEW_USER) @Valid UserDTO userDTO,
	                           BindingResult bindingResult,
	                           Locale locale) {
		String pagePath;
		if(!bindingResult.hasErrors()) {
			try {
				User user = EntityBuilder.buildUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getLogin(), userDTO.getPassword_1(), null, null, null);
				if (userService.checkIsNewUser(user.getLogin())) {
					userService.bookUser(user);
					modelMap.addAttribute(Parameters.SUCCESS_OPERATION, messageSource.getMessage("message.successoperation", null, locale));
					pagePath = pagePathManager.getProperty(PagePath.HOME_PAGE_PATH);
				} else {
					modelMap.addAttribute(Parameters.ERROR_USER_EXISTS, messageSource.getMessage("message.userexsistserror", null, locale));
					pagePath = pagePathManager.getProperty(PagePath.REGISTRATION_PAGE_PATH);
				}
			} catch (ServiceException e) {
				modelMap.addAttribute(Parameters.ERROR_DATABASE, messageSource.getMessage("message.databaseerror",null, locale));
				pagePath = pagePathManager.getProperty(PagePath.ERROR_PAGE_PATH);
			} catch (NullPointerException e) {
				logger.error("Error in User Controller register method: ", e);
				pagePath = pagePathManager.getProperty(PagePath.HOME_PAGE_PATH);
			}
		} else {
			pagePath = pagePathManager.getProperty(PagePath.REGISTRATION_PAGE_PATH);
		}
		return pagePath;
	}
}
