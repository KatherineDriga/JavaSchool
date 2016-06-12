<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>UberBahn Buy Ticket</title>
    <style>
        #header {
            background-color:#465c71;
            color:white;
            text-align:center;
            padding:3px;
            height: 150px;
        }
        #nav {
            line-height:30px;
            background-color:#465c71;
            height:500px;
            width:300px;
            float:left;
            padding:5px;
        }
        #section {
            width:350px;
            float:left;
            padding:20px;
        }
        #footer {
            background-color:#465c71;
            color:white;
            clear:both;
            text-align:center;
            padding:3px;
        }
        div.menu
        {
            padding: 4px 0px 4px 8px;
            display: inline;
            float:right;
            align: bottom;
        }
        div.menu ul
        {
            list-style: none;
            margin: 0px;
            padding: 0px;

        }
        ul.menuclass li{
            display:inline;
        }
        div.menu ul li a, div.menu ul li a:visited
        {
            background-color: #465c71;
            border: 1px #4e667d solid;
            color: #dde4ec;
            display: inline;
            line-height: 1.35em;
            padding: 4px 20px;
            text-decoration: none;
            white-space: nowrap;
        }
        div.menu ul li a:hover
        {
            background-color: #bfcbd6;
            color: #465c71;
            text-decoration: none;
        }
        div.menu ul li a:active
        {
            background-color: #465c71;
            color: #cfdbe6;
            text-decoration: none;
        }

        .form_element{
            margin: 5px 5px;
        }
        .form_element label{
            float: left;
            width: 100px;
            color: #cfdbe6;
        }
        .form_element input[type="submit"] {
            background: #bfcbd6;
            color: #465c71;
            border: 1px solid #ccc;
            padding: 5px 10px;
            width: 175px;
            float: right;
            margin-top: 15px;
            margin-right: 15px;
        }
        img{
            width: 150px;
            height: 120px;
            float: left;
            margin: 0;
        }

    </style>
</head>
<body>
<div id="header">
    <p><img src="/images/IMG_Train_Logotype.jpg" alt="Изображение не найдено"></p>
    <h1>UberBahn</h1>
    <br>
    <br>
    <div class="menu">
        <ul class="menuclass">
            <li><a href="/">About us</a></li>
            <li><a href="/">Passenger information</a></li>
            <li><a href="/">Timetable</a></li>
            <li><a href="/">Career</a></li>
            <li><a href="/">Contacts</a></li>
        </ul>
    </div>
</div>

<div id="nav">
    <br>
    <br>
    <form class="login_form" method="post">
        <p><div class="form_element"><label>Login</label><input type="text" required></div></p>
        <p><div class="form_element"><label>Password</label><input type="password" required></div></p>
        <div class="form_element" id="login_button"><input type="submit" value="Log in"></div>
    </form>
</div>

<div id="section">
    <ul>
        <li><a href="/stationTimetable">StationTimetable</a></li>
        <li><a href="/trainTimetable">TrainTimetable</a></li>

    </ul>
</div>

<div id="footer">
    Copyright © http://www.uberbahn.com/
</div>
</body>

</html>
