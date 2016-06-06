<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UberBahn Buy Ticket</title>
    <style>
        #header {
            background-color:green;
            color:white;
            text-align:center;
            padding:3px;
        }
        #nav {
            line-height:30px;
            background-color:silver;
            height:500px;
            width:270px;
            float:left;
            padding:5px;
        }
        #section {
            width:350px;
            float:left;
            padding:20px;
        }
        #footer {
            background-color:green;
            color:white;
            clear:both;
            text-align:center;
            padding:3px;
        }
    </style>
</head>
<body>
<div id="header">
    <h1>UberBahn</h1>
</div>
<div id="nav">
    item1<br>
    item2<br>
    item3<br>

</div>

<div id="section">

    <h1>Ticket Purchase Form</h1>

        <form class="subform"  method="post">
        <table>
        <tr>
            <th>Route information</th>
            <th>Passenger information</th>
        </tr>
            <tr>
                <td>
                    <label for="dateOfTravel" class="label">Date of Travel</label>
                    <input type="date" name="dateOfTravel" id="dateOfTravel">
                </td>

                <td>
                    <label for="fname" class="label">First Name</label>
                    <input type="text" name="fname" id="fname">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="routeNumber" class="label">Route Number</label>
                    <select name="routeNumber" id="routeNumber">
                        <option value="null">Select</option>
                        <%
                            String url="jdbc:mysql://localhost/uberbahn";
                            String user="uberbahn_webapp";
                            String password="123";

                            Connection con=null;

                            try {
                                con=DriverManager.getConnection(url, user, password);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            try {
                                PreparedStatement pstmt = con.prepareStatement(
                                        "select * from Route");
                                ResultSet rs=pstmt.executeQuery();

                                while(rs.next())
                                {
                        %>

                        <option value="<%=rs.getString(2)%>"><%=rs.getString(2)%></option>

                        <%
                            }
                        %>

                        <%

                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        %>

                    </select>
                </td>

                <td>
                    <label for="lname" class="label">Last Name</label>
                    <input type="text" name="lname" id="lname">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="stationOfDeparture" class="label">Station of Departure</label>
                    <select name="stationOfDeparture" id="stationOfDeparture">
                        <option value="null">Select</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </td>
                <td>
                    <label for="dateOfBirth" class="label">Date of Birth</label>
                    <input type="date" name="dateOfBirth" id="dateOfBirth">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="stationOfArrival" class="label">Station Of Arrival</label>
                    <select name="stationOfArrival" id="stationOfArrival">
                        <option value="null">Select</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </td>
            </tr>


    </table>
    <p>
        <label for="comments" class="label">Additional comments </label>
        <textarea name="comments" cols="35" rows="4" id="comments"></textarea>
    </p>
    <p>
        <input type="submit" value="Buy Ticket">
    </p>
    </form>
</div>

<div id="footer">
    Copyright © http://www.uberbahn.com/
</div>
</body>
</html>
