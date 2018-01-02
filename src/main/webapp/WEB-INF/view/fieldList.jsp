<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Lista boisk</h1>
<table>
    <tr>
        <th>ID</th>
        <th>NAZWA</th>
        <th>MIASTO</th>
        <th>ULICA</th>
        <th>NUMER</th>
    </tr>
    <c:forEach items="${fields}" var="field">
        <tr>
            <td>${field.fieldId}</td>
            <td>${field.name}</td>
            <td>${field.city}</td>
            <td>${field.street}</td>
            <td>${field.number}</td>
        </tr>
    </c:forEach>
</table>