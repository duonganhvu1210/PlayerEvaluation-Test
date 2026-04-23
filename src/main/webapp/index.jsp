<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Add Player</h2>

<form method="post" action="player">
    Name: <input type="text" name="name"/><br/>
    Full Name: <input type="text" name="fullName"/><br/>
    Age: <input type="text" name="age"/><br/>

    Index:
    <select name="indexId">
        <option value="1">Speed</option>
        <option value="2">Strength</option>
        <option value="3">Accurate</option>
    </select>

    <button type="submit">Add</button>
</form>

<h3 style="color:red">${error}</h3>

<hr/>

<h2>Player List</h2>

<table border="1">
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
        <a href="player?action=delete&id=${p.id}">Delete</a>
    </td>
</tr>
</c:forEach>
</table>