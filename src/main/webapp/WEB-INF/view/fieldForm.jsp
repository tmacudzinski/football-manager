<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Witaj, wprowadź szczegóły boiska</h1>
<form:form method="POST" action="/addField" modelAttribute="field">
    <table>
        <tr>
            <td><form:label path="name">Nazwa boiska</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="city">Miasto</form:label></td>
            <td><form:input path="city"/></td>
        </tr>
        <tr>
            <td><form:label path="street">Ulica</form:label></td>
            <td><form:input path="street"/></td>
        </tr>
        <tr>
            <td><form:label path="number">Numer</form:label></td>
            <td><form:input path="number"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Dodaj boisko"/></td>
        </tr>
    </table>
</form:form>