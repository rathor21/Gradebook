<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type=text/css href="custom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gradebook</title>
</head>
<body>
	<div class="container">
		<h2>Gradebook</h2>
		<form role="form" action="Gradebook" method="post">
			<div class="form-group">
				<label for="assignment">Assignment:</label> <input type="text"
					class="form-control" name="assignment"
					placeholder="Enter Assignment">
			</div>
			<div class="form-group">
				<label for="grade">Grade:</label> <input type="number"
					class="form-control" name="grade" placeholder="Enter Grade">
			</div>
		
			<button type="submit" class="btn btn-default" name="submit">Submit</button>
				</form>
				
				<form role="form" action="GradeTransaction" method="post">
			<button type="submit" class="btn btn-default" name="history">History</button>
			</form>
						
				<form role="form" action="GradeAverage" method="post">
			<button type="submit" class="btn btn-default" name="average">Average</button>
			
	</form>
	</div>
	
</body>
</html>

