<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Add / Update Player</h2>

<form method="post" action="player">

    <input type="hidden" name="id" value="${player.id}"/>

    Name:
    <input type="text" name="name" value="${player.name}"/><br/>

    Full Name:
    <input type="text" name="fullName" value="${player.fullName}"/><br/>

    Age:
    <input type="text" name="age" value="${player.age}"/><br/>

    Index:
    <select name="indexId">

        <option value="1"
        <c:if test="${player.indexId == 1}">selected</c:if>>
            Speed
        </option>

        <option value="2"
        <c:if test="${player.indexId == 2}">selected</c:if>>
            Strength
        </option>

        <option value="3"
        <c:if test="${player.indexId == 3}">selected</c:if>>
            Accurate
        </option>

    </select>

    <br/><br/>

    <button type="submit">
        <c:choose>
            <c:when test="${player == null}">Add</c:when>
            <c:otherwise>Update</c:otherwise>
        </c:choose>
    </button>

</form>

<h3 style="color:red">${error}</h3>

<hr/>

<h2>Player List</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Full Name</th>
        <th>Age</th>
        <th>Action</th>
    </tr>

    <c:forEach var="p" items="${list}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.fullName}</td>
            <td>${p.age}</td>
            <td>
                <a href="player?action=edit&id=${p.id}">Edit</a> |
                <a href="player?action=delete&id=${p.id}"
                   onclick="return confirm('Delete this player?')">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>

</table>