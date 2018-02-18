<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />


	<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Wetter Demo</title>
<SCRIPT src="js/jquery-3.2.1.js"></SCRIPT>
<script>
	$(document).ready(function() {
		window.alert("Document ready");
	});
</script>
</head>
<body>
	<h3>
		<span>${welcomemessage}</span> am <SPAN>${welcomedate}</SPAN>
	</h3>

	<spring:url value="/staedte" var="searchUrl" />

	<SELECT id="landlist">
		<OPTION value="empty"></OPTION>
		<OPTION value="Austria">Austria</OPTION>
		<OPTION value="Iceland">Iceland</OPTION>
		<OPTION value="Sweden">Sweden</OPTION>
		<OPTION value="Norway">Norway</OPTION>
	</SELECT>

	<form:form action="${searchUrl}" modelAttribute="searchland"
		method="post">
		<form:input title="Land" path="string1" />
		<form:button>suchen</form:button>
	</form:form>


	<c:forEach items="${list}" var="stadt">
		<spring:url value="/wetter/${stadt}" var="wetterUrl" />
		<UL>
			<LI><a href="${wetterUrl}">${stadt}</a></LI>
		</UL>
	</c:forEach>

	<SELECT class="stadtlist" id="stadtlistAustria" style="display: none">
		<c:forEach items="${listAustria}" var="stadt">
			<OPTION value="${stadt}">${stadt}</OPTION>
		</c:forEach>
	</SELECT>
	<SELECT class="stadtlist" id="stadtlistIceland" style="display: none">
		<c:forEach items="${listIceland}" var="stadt">
			<OPTION value="${stadt}">${stadt}</OPTION>
		</c:forEach>
	</SELECT>
	<SELECT class="stadtlist" id="stadtlistSweden" style="display: none">
		<c:forEach items="${listSweden}" var="stadt">
			<OPTION value="${stadt}">${stadt}</OPTION>
		</c:forEach>
	</SELECT>
	<SELECT class="stadtlist" id="stadtlistNorway" style="display: none">
		<c:forEach items="${listNorway}" var="stadt">
			<OPTION value="${stadt}">${stadt}</OPTION>
		</c:forEach>
	</SELECT>



	<SCRIPT type="application/javascript">
		
		
		
		
		
		
		
		
		
		
		
		
				$("LI").hover(function(){        
			        $(this).css("font-weight", "bold");   
			    }, function(){
			    	$(this).css("font-weight", "normal");  
				});

				$("#landlist").change(function(){
					var optionSelected = $("option:selected", this);
				    var valueSelected = this.value;
				    $('.stadtlist').hide();	
				    $('#stadtlist'+valueSelected.toString()).show();
					});


				function displayVals() {
					  var singleValues = $( "#stadtlist" ).val();

					  $( "p" ).html( "<b>Stadt:</b> " + singleValues );
					}
					 
					$( "#stadtlist" ).change( displayVals );
					displayVals();
			

					
	
	
	
	
	
	
	
	
	
	
	</SCRIPT>

	<HR />
	<p>${wetter}</p>

</body>
	</html>
</jsp:root>