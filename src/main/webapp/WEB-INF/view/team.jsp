<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<h1>${team.name}</h1>
<table id="playersTable">
    <tr>
        <th>IMIĘ</th>
        <th>NAZWISKO</th>
        <th colspan="2">AKCJA</th>
    </tr>
    <tr>
        <td colspan="2">
            <select id="selectNewPlayer">
                <c:forEach items="${freePlayers}" var="freePlayer">
                    <option value="${freePlayer.playerId}">${freePlayer.firstname} ${freePlayer.lastname}</option>
                </c:forEach>
            </select>
        </td>
        <td colspan="2"><button onClick="addPlayer(${team.teamId})">Dodaj do drużyny</button></td>
    </tr>
    <div>
        <c:forEach items="${team.players}" var="player">
            <tr id="player${player.playerId}">
                <td colspan="2">${player.firstname} ${player.lastname}</td>
                <td><a href="<c:url value="/player/${player.playerId}"/>">Szczegóły</a></td>
                <td><button onClick="deletePlayer(${team.teamId}, ${player.playerId})">Usuń z drużyny</button></td>
            </tr>
        </c:forEach>
    </div>
</table>

<script >
    function deletePlayer(teamId, playerId) {
        var playerDivId = "player" + playerId;
        var e = document.getElementById("selectNewPlayer");
        var opt = document.createElement('option');
        opt.value = playerId;
        opt.innerHTML = document.getElementById(playerDivId).firstChild.innerText;
        e.appendChild(opt);

        $.ajax({
            type : "GET",
            url  : "/team/" + teamId + "/deletePlayer/" + playerId,
            success : function(){
                var rowToDelete = document.getElementById(playerDivId);
                rowToDelete.parentNode.removeChild(rowToDelete);
            },
            error : function(xhr, errmsg) {
                alert("No values found!");
            }
        });

        var removingPlayerDiv = document.getElementById(playerDivId);
        removingPlayerDiv.innerHTML = '<td colspan="4"><div class="loader"></div></td>';
    }

    function addPlayer(teamId) {
        var e = document.getElementById("selectNewPlayer");
        var selectedOption = e.options[e.selectedIndex];
        var playerId = selectedOption.value;
        var playerName = selectedOption.innerHTML;
        e.removeChild(selectedOption);
        var playerDivId = "player" + playerId;

        var table = document.getElementById("playersTable");
        var row = table.insertRow(-1);
        row.id = playerDivId;
        var loaderCell = row.insertCell(0);
        loaderCell.colSpan = 4;
        loaderCell.innerHTML = '<div class="loader"></div>';

        $.ajax({
            type : "POST",
            url  : "/team/" + teamId + "/addPlayer/" + playerId,
            success : function(){
                row.deleteCell(0);
                var cellName = row.insertCell(0);
                cellName.innerHTML = playerName;
                cellName.colSpan = 2;
                var detailsCell = row.insertCell(-1);
                detailsCell.innerHTML = '<a href="<c:url value="/player/' + playerId + '"/>">Szczegóły</a>';
                var deleteCell = row.insertCell(-1);
                deleteCell.innerHTML = '<button onClick="deletePlayer(' + teamId + ', ' + playerId + ')">Usuń z drużyny</button>';
            },
            error : function(xhr, errmsg) {
                alert("No values found!");
            }
        });
    }
</script>