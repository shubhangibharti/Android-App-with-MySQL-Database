<?php 
$conn=mysqli_connect("hostname","username","password","databasename");
if(!$conn) {
        die('Could not connect: ' . mysql_error());
    }
    mysqli_select_db($conn, "databasename");
?>
