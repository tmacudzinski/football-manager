<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Lista umiejętności</h1>
<table>
    <tr>
        <th>ID</th>
        <th>NAZWA</th>
    </tr>
    <c:forEach items="${skills}" var="skill">
        <tr>
            <td>${skill.skillId}</td>
            <td>${skill.name}</td>
        </tr>
    </c:forEach>
</table>