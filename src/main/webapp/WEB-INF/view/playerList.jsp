<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Lista piłkarzy</h1>
<table>
    <tr>
        <th>ID</th>
        <th>IMIĘ</th>
        <th>NAZWISKO</th>
        <th>DRUŻYNA</th>
        <th>UMIEJĘTNOŚCI</th>
    </tr>
    <c:forEach items="${players}" var="player">
        <tr>
            <td>${player.playerId}</td>
            <td>${player.firstname}</td>
            <td>${player.lastname}</td>
            <td>${player.team.name}</td>
            <td>
                <c:forEach items="${player.skills}" var="skill">
                    <p>${skill.name}</p>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>