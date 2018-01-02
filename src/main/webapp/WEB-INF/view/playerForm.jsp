<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1>Wprowadź szczegóły zawodnika</h1>
<form:form method="POST" action="/addPlayer" modelAttribute="player">
    <table>
        <tr>
            <td><form:label path="firstname">Imię</form:label></td>
            <td><form:input path="firstname"/></td>
        </tr>
        <tr>
            <td><form:label path="lastname">Nazwisko</form:label></td>
            <td><form:input path="lastname"/></td>
        </tr>
        <tr>
            <td><form:label path="team">Drużyna</form:label></td>
            <td>
                <form:select path="team">
                    <form:options items="${teams}" itemValue="teamId" itemLabel="name" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="skills">Umiejętności</form:label></td>
            <td>
                <form:select path="skills" multiple="true">
                    <form:options items="${skills}" itemValue="skillId" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Dodaj zawodnika"/></td>
        </tr>
    </table>
</form:form>