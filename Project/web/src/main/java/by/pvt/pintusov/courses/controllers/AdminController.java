package by.pvt.pintusov.courses.controllers;

import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.Parameters;
import by.pvt.pintusov.courses.filters.PaginationFilter;
import by.pvt.pintusov.courses.managers.PagePathManager;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Project: courses
 * Created by: USER
 * Date: 17.02.17.
 */

@Controller
@RequestMapping("/admin")
@SessionAttributes("filter")
public class AdminController {

	@Autowired
	private IUserService userService;
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

	@GetMapping(value = "/main")
	public String showAdminMainPage (ModelMap modelMap) {
		modelMap.addAttribute(Parameters.USER, principalUtil.getPrincipal());
		return pagePathManager.getProperty(PagePath.ADMIN_PAGE_PATH);
	}
}
