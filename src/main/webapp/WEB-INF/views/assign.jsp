<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Affectation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"  crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h2 class="text-center">Employee Affectation</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/assign" method="post">
                <div class="form-group">
                    <label for="employeId">Employee Name:</label>
                    <select name="employeId" id="employeId" class="form-control" required>
                        <c:forEach var="employe" items="${employes}">
                            <option value="${employe.id}">${employe.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="projectId">Project Name:</label>
                    <select name="projectId" id="projectId" class="form-control" required>
                        <c:forEach var="project" items="${projects}">
                            <option value="${project.id}">${project.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="implication">Implication (%):</label>
                    <input type="number" name="implication" id="implication" class="form-control" min="0" max="100" required>
                </div>
                <button type="submit" class="btn btn-success">Assign Project</button>
            </form>
            <a href="${pageContext.request.contextPath}/employe-list" class="btn btn-secondary mt-3">Employees List</a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-danger mt-3">Back to Home</a>
        </div>
    </div>
</div>
</body>
</html>
