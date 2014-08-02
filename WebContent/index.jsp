<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="db.spot.client.Client"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Data-Process</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Data Enhance</h1>
		<form class="form-inline" action="result.jsp" method="GET">
			<div class="row">
				<div class="col-xs-6 col-sm-4">
					<label for="inputEmail3" class="control-label">Confidence
						level</label> <input type="text" class="form-control" name="confidence"
						value="0"></input>
				</div>
				<div class="col-xs-6 col-sm-4">
					<label for="inputEmail3" class="control-label">Support
						level</label> <input type="text" class="form-control" name="support"
						value="20"></input>
				</div>
				<div class="col-xs-6 col-sm-4">
					<select class="form-control" name="server">
						<option>http://spotlight.dbpedia.org/</option>
						<option>http://localhost:2222/</option>
					</select>
				</div>
			</div>
			</br>
			<div class="row">
				<textarea class="form-control" rows="3" name="input" style="width:1000px;" ></textarea>
			</div>
			</br>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-8"></div>
				<div class="col-xs-6 col-md-4">
					<input type="submit" value="Submit" /></input>
				</div>
			</div>
		</form>
	</div>
</body>
</html>