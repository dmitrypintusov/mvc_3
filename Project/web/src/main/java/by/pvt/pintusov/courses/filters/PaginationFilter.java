package by.pvt.pintusov.courses.filters;

import lombok.Data;

/**
 * Project: courses
 * Created by: USER
 * Date: 16.02.17.
 */
@Data
public class PaginationFilter {
	private String ordering;
	private String direction;
	private Integer currentPage;
	private Integer recordsPerPage;
}
