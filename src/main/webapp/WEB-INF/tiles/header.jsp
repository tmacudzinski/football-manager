<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Aplikacja piłkarska</h1>
<div id="menu">
    <a href="<c:url value="/listTeams"/>">Lista Drużyn</a>
    <a href="<c:url value="/addTeam"/>">Dodaj Drużynę</a>
    <div class="separator"></div>
    <a href="<c:url value="/listFields"/>">Lista Boisk</a>
    <a href="<c:url value="/addField"/>">Dodaj Boisko</a>
    <div class="separator"></div>
    <a href="<c:url value="/listSkills"/>">Lista Umiejętności</a>
    <a href="<c:url value="/addSkill"/>">Dodaj Umiejętność</a>
    <div class="separator"></div>
    <a href="<c:url value="/listPlayers"/>">Lista Piłkarzy</a>
    <a href="<c:url value="/addPlayer"/>">Dodaj Piłkarza</a>
</div>