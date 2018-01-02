<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Wprowadź szczegóły drużyny</h1>
<form:form method="POST" action="/addTeam" modelAttribute="team">
    <table>
        <tr>
            <td><form:label path="name">Nazwa drużyny</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="field">Boisko</form:label></td>
            <td>
                <form:select path="field" >
                    <form:options items="${fields}" itemValue="fieldId" itemLabel="name" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Dodaj drużynę"/></td>
        </tr>
    </table>
</form:form>