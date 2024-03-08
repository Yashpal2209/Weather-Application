<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather App</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="style.css" />

</head>
<body style="background-color:blueviolet;">
<div style="color:black; text-align:center">
    
    <h1>Welcome to the Weather Web </h1>
 
     <form action="MyServlet" method="post" class="searchInput">
         <label><h4>Enter the city name</h4></label>
         <input type="text" placeholder="Enter City Name" id="searchInput" name="city"/><br>
         <label><h4>Enter the days</h4></label>
            <select id="GET Details" name="GET Details" required>
                <option value="Today">Today</option>
                <option value="1 Week">1Week</option>
                <option value="One month">Month</option>
            </select><br><br>
            <button id="searchButton" value="Submit"><i class="fa-solid fa-magnifying-glass"></i>GET</button>
      </form>    
 </div>
 
</body>
</html>
