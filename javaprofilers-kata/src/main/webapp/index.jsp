<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c" %> 


	<%
	    List<URL> visitedURLs = (List<URL>) session
			    .getAttribute("visitedURLs");
	    if (visitedURLs == null) {
			visitedURLs = new ArrayList<URL>();
	    }

	    String file = request.getRequestURI();
	    if (request.getQueryString() != null) {
			file += '?' + request.getQueryString();
	    }
	    URL reconstructedURL = new URL(request.getScheme(),
			    request.getServerName(), request.getServerPort(), file);

	    visitedURLs.add(reconstructedURL);
	    session.setAttribute("visitedURLs", visitedURLs);
	%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index 1</title>
</head>
<body>
<h1>These are the pages you've visited before: </h1>
<ul>
	<% for (URL visitedURL : visitedURLs) { %>
		<li><%= visitedURL %></li>
	<% } %>
</ul>
</body>
	</html>