
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Страница преподавателя</title>
    </head>
    <body>
        <h2>${user.firstName} ${user.lastName}</h2>
        <h3>Вы вошли в систему как преподаватель</h3>
        <h4>Выберите операцию:</h4>
        <a href="controller?command=show_students">Показать список студентов</a> <br>
        <a href="controller?command=put_mark">Поставить оценку студенту</a> <br>
        <a href="controller?command=open_course">Открыть курс</a> <br>
        <a href="controller?command=close_course">Закрыть курс</a> <br>
        <a href="controller?command=logout">Выйти из системы</a>
    </body>
</html>
