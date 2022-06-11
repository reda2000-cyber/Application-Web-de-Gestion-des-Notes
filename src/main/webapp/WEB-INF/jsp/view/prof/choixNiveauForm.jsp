<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">



			<jsp:include page="../fragments/profmenu.jsp" />

		</div>
	</nav>



        <form:form action="/prof/choixNiveau" modelAttribute="Niveau">
        
        
        <h2>Fichiers de collecte des notes des délibérations finales</h2>
        
        <br>
        
 
        <label>Choisir la Classe ou le Niveau :</label>
        <br>
        
       <select class="form-control" name="Niveau" >
                    <c:forEach  items="${listniveau}" var="listniveau" >
                            <option value="${listniveau.idNiveau}">${listniveau.titre}</option>
                    </c:forEach>
        </select>
        <br>
        
        <input type="submit" value="Générer" />  
    </form:form>  


	</div>



<jsp:include page="../fragments/userfooter.jsp" />