package by.pvt.pintusov.courses.controllers;

import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project: courses
 * Created by: USER
 * Date: 21.02.17.
 */
@RestController
public class UserRestController {
private static Logger logger = Logger.getLogger(UserRestController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> getUsers () {
		try {
			List<User> users = userService.getAll();
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (ServiceException e) {
			logger.error(e);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User>
}
