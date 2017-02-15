package by.pvt.pintusov.courses.utils;

import by.pvt.pintusov.courses.constants.OrderingConstants;
import by.pvt.pintusov.courses.constants.Parameters;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 15.02.2017.
 */
public class OrderingUtil {

	private OrderingUtil () {}

	public static String defineOrderingType (String ordering) {
		String orderBy;
		switch (ordering) {
			case "courseName":
				orderBy = OrderingConstants.ORDER_BY_COURSE_NAME;
				break;
			case "courseStatus":
				orderBy = OrderingConstants.ORDER_BY_COURSE_STATUS;
				break;
			case "hours":
				orderBy = OrderingConstants.ORDER_BY_HOURS;
				break;
			case "startDate":
				orderBy = OrderingConstants.ORDER_BY_START_DATE;
				break;
			case "updateDate":
				orderBy = OrderingConstants.ORDER_BY_UPDATE_DATE;
				break;
			default:
				orderBy = OrderingConstants.ORDER_BY_ID;
		}
		return orderBy;
	}

	public static String defineOrderingDirection (String direction) {
		String orderDirection;
		switch (direction) {
			case "asc":
				orderDirection = OrderingConstants.ORDER_ASC;
				break;
			case "desc":
				orderDirection = OrderingConstants.ORDER_DESC;
				break;
			default:
				orderDirection = OrderingConstants.ORDER_ASC;
		}
		return orderDirection;
	}
}
