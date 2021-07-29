<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Curso Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Cursos</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${curso != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${curso == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${curso != null}">
                                    Editar Curso
                                </c:if>
                                <c:if test="${curso == null}">
                                    Agregar Nuevo Curso
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${curso != null}">
                            <input type="hidden" name="id" value="<c:out value='${curso.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Nombre de Curso</label> <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Nombre de Profesor</label> <input type="text" value="<c:out value='${user.email}' />" class="form-control" name="email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Semestre</label> <input type="text" value="<c:out value='${user.country}' />" class="form-control" name="country">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Pre_requisito</label> <input type="text" value="<c:out value='${user.country}' />" class="form-control" name="country">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Guardar</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>