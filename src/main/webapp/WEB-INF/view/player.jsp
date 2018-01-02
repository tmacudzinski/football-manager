<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<h1>${player.firstname} ${player.lastname}</h1>
<table id="skillsTable">
    <tr>
        <th>NAZWA UMIEJĘTNOŚCI</th>
        <th>AKCJA</th>
    </tr>
    <tr>
        <td>
            <select id="selectNewSkill">
                <c:forEach items="${freeSkills}" var="freeSkill">
                    <option value="${freeSkill.skillId}">${freeSkill.name}</option>
                </c:forEach>
            </select>
        </td>
        <td><button onClick="addSkill(${player.playerId})">Dodaj umiejętność</button></td>
    </tr>
    <div>
        <c:forEach items="${player.skills}" var="skill">
            <tr id="skill${skill.skillId}">
                <td>${skill.name}</td>
                <td><button onClick="deleteSkill(${player.playerId}, ${skill.skillId})">Usuń umiejętność</button></td>
            </tr>
        </c:forEach>
    </div>
</table>

<script >
    function deleteSkill(playerId, skillId) {
        var skillDivId = "skill" + skillId;
        var e = document.getElementById("selectNewSkill");
        var opt = document.createElement('option');
        opt.value = skillId;
        opt.innerHTML = document.getElementById(skillDivId).firstChild.innerText;
        e.appendChild(opt);

        $.ajax({
            type : "GET",
            url  : "/player/" + playerId + "/deleteSkill/" + skillId,
            success : function(){
                var rowToDelete = document.getElementById(skillDivId);
                rowToDelete.parentNode.removeChild(rowToDelete);
            },
            error : function(xhr, errmsg) {
                alert("No values found!");
            }
        });

        var removingSkillDiv = document.getElementById(skillDivId);
        removingSkillDiv.innerHTML = '<td colspan="2"><div class="loader"></div></td>';
    }

    function addSkill(playerId) {
        var e = document.getElementById("selectNewSkill");
        var selectedOption = e.options[e.selectedIndex];
        var skillId = selectedOption.value;
        var skillName = selectedOption.innerHTML;
        e.removeChild(selectedOption);
        var skillDivId = "skill" + skillId;

        var table = document.getElementById("skillsTable");
        var row = table.insertRow(-1);
        row.id = skillDivId;
        var loaderCell = row.insertCell(0);
        loaderCell.colSpan = 2;
        loaderCell.innerHTML = '<div class="loader"></div>';

        $.ajax({
            type : "POST",
            url  : "/player/" + playerId + "/addSkill/" + skillId,
            success : function(){
                row.deleteCell(0);
                var cellName = row.insertCell(0);
                cellName.innerHTML = skillName;
                var deleteCell = row.insertCell(-1);
                deleteCell.innerHTML = '<button onClick="deleteSkill(' + playerId + ', ' + skillId + ')">Usuń umiejętność</button>';
            },
            error : function(xhr, errmsg) {
                alert("No values found!");
            }
        });
    }
</script>