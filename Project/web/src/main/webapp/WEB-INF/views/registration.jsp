
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/WEB-INF/assets/pages/error/error.jsp" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
        <form name="registration" method="POST" action="controller">
            <input type="hidden" name="command" value="registration" />
            Введите ваши данные:<br>
            Имя:<br>
            <input type="text" name="firstname" value="" size="20" required /><br>
            Фамилия:<br>
            <input type="text" name="lastname" value="" size="20" required /><br>
            Логин:<br>
            <input type="login" name="login" value="" size="20" required /><br>
            Пароль:<br>
            <input type="password" name="password" value="" size="20" required /><br>
            <input type="radio" name="accessLevel" value="STUDENT" checked />Student
            <input type="radio" name="accessLevel" value="TEACHER" />Teacher<br>
            ${operationMessage}
            ${errorUserExists} <br>
            <input type="submit" value="Зарегистрировать" />
            <button><a href="controller?command=back">Вернуться обратно</a></button>
        </form>
    </body>
</html>