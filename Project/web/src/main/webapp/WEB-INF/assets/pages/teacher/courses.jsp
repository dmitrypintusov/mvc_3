
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Курсы</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table border="1">
            <tr bgcolor="#f0ffff">
                <td align="center"><strong>Название курса</strong></td>
                <td><strong>Описание</strong></td>
                <td><strong>Номер пользователя</strong></td>
                <td><strong>Номер курса</strong></td>
            </tr>
            <c:forEach var="course" items="${coursesList}">
                <tr>
                    <td><c:out value="${course.id}" /></td>
                    <td><c:out value="${course.courseName}" /></td>
                    <td><c:out value="${course.courseStatus}" /></td>
                    <td><c:out value="${course.hours}" /></td>
                    <td><c:out value="${course.startDate}" /></td>
                    <td><c:out value="${course.updateDate}" /></td>
                </tr>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${currentPage != 1}">
                <td><a href="controller?command=courses_list&currentPage=${currentPage - 1}">На предыдущую</a></td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>


        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="controller?command=courses_list&currentPage=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
        <br>

        <c:choose>
            <c:when test="${currentPage lt numberOfPages}">
                <td><a href="controller?command=courses_list&currentPage=${currentPage + 1}">На следующую</a></td>
            </c:when>
            <c:otherwise>
                <td></td>
            </c:otherwise>
        </c:choose>

        <form name="operationsForm" method="POST" action="controller">
            <input type="hidden" name="command" value="courses_list" />
            <input type="text" name="currentPage" value="" size="3"/>
            <input type="submit" value="Перейти" />
        </form>

        <form name="recordsPerPageForm" method="POST" action="controller">
            <input type="hidden" name="command" value="courses_list"/>
            <select name="recordsPerPage">
                <option value="3">3</option>
                <option value="5">5</option>
                <option value="10">10</option>
            </select>
            <input type="submit" value="Обновить" />
        </form>

        <form name="orderingForm" method="POST" action="controller">
            <input type="hidden" name="command" value="courses_list"/>
            <select name="ordering">
                <option value="courseName">по названию курса</option>
                <option value="courseStatus">по статусу курса</option>
                <option value="hours">по продолжительности</option>
                <option value="startDate">по дате начала</option>
                <option value="updateDate">по дате обновления</option>
            </select>
            <input type="submit" value="Обновить" />
        </form>

        <br>
        <a href="controller?command=back_teacher">Вернуться обратно</a>
        <a href="controller?command=logout">Выйти из системы</a>
    </body>
</html>
