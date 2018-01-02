<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Wprowadź szczegóły umiejętności</h1>
<form:form method="POST" action="/addSkill" modelAttribute="skill">
    <table>
        <tr>
            <td><form:label path="name">Nazwa umiejętności</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Dodaj umiejętność"/></td>
        </tr>
    </table>
</form:form>