<%--
  Created by IntelliJ IDEA.
  User: emil
  Date: 11/13/16
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Search</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="scripts.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <link rel="stylesheet" type="text/css" href="styles.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
          integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
</head>
<body>

<form method="POST">
    <div class="form-group">
        <label for="inputManufacturer">Manufacturer</label>
        <input type="text" class="form-control" id="inputManufacturer" aria-describedby="emailHelp"
               placeholder="Enter Manufacturer">
    </div>

    <div class="form-group">
        <label for="inputModel">Model</label>
        <input type="text" class="form-control" id="inputModel" aria-describedby="emailHelp"
               placeholder="Enter Model">
    </div>



    <div class="form-group">
        <label for="engineType">Engine Type</label>
        <select class="form-control" id="engineType">
            <option>Diesel</option>
            <option>Gasoline</option>
            <option>Hybrid</option>
            <option>Electric</option>
            <option>LPG</option>
        </select>
    </div>


    <div class="form-group">
        <label for="yearSelect">Year</label>
        <select class="form-control" id="yearSelect">
            <script>
                var myDate = new Date();
                var year = myDate.getFullYear();
                for(var i = 1900; i <= year; i++){
                    document.write('<option value="'+i+'">'+i+'</option>');
                }
            </script>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<div class="container">
    <h2>Cars Table</h2>

    <table class="table" id="carsTable">
        <thead>
        <tr>
            <th>Manufacturer</th>
            <th>Model</th>
            <th>Engine Type</th>
            <th>Year</th>
        </tr>
        </thead>

    </table>

    <div id="noMoreToLoad">
    </div>
</div>

</body>
</html>
