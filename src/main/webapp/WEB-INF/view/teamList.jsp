<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Lista drużyn</h1>
<table>
    <tr>
        <th>ID</th>
        <th>NAZWA</th>
        <th>BOISKO</th>
    </tr>
    <c:forEach items="${teams}" var="team">
        <tr>
            <td>${team.teamId}</td>
            <td>${team.name}</td>
            <td>
                <p><strong>${team.field.name}</strong>
                <p>${team.field.city}</p>
            </td>
            <td><a href="<c:url value="/team/${team.teamId}"/>">Piłkarze</a></td>
        </tr>
    </c:forEach>
</table>