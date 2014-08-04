<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="db.spot.client.Client"%>
<%@ page import="org.json.JSONArray"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Result</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Data Enhanced Result</h1>

		<%
			Client c = new Client();
			
			long start = System.currentTimeMillis();
			JSONArray output = c.process(request.getParameter("server"),
					request.getParameter("input"),
					Double.parseDouble(request.getParameter("confidence")),
					Integer.parseInt(request.getParameter("support")));
			long end = System.currentTimeMillis();
			long differ = (end - start);
		%>
		<div class="alert alert-info"><%=request.getParameter("input")%></div>
		<table class="table table-hover">
			<tr>
				<th>Actual name</th>
				<th>Enhanced Name</th>
				<th>Word Position</th>
				<th>Category</th>
			</tr>
			<%
				for (int i = 0; i < output.length(); i++) {
			%>
			<tr class="active"></tr>
			<tr>
				<td><%=output.getJSONObject(i).get("@surfaceForm")%></td>
				<td><a href=<%=output.getJSONObject(i).get("@URI")%>><%=output.getJSONObject(i).get("@URI").toString()
						.substring(28)%></a></td>
				<td><%=output.getJSONObject(i).get("@offset")%></td>
				<td>	
				<% String [] res = c.getCate(output.getJSONObject(i).get("@types").toString());%>
				<%for(int j = 0; j < res.length; j++){
					%>
					<table>
					<tr><%=res[j]%></tr>
					</table>
				<% } %>
				</td>
			</tr>
			
			<%
				}
			%>

		</table>
		<div class="alert alert-info">Process Duration :  <%=differ%> millisecond</div>
	</div>
</body>
</html>