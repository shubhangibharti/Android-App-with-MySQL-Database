<?php
	
include "connection.php";


$order_id=$_POST["orderid"];
$restaurant_id=$_POST["restid"];
$user_id=$_POST["uid"];
$address=$_POST["add"];
$order_detail=$_POST["orderdetail"];
$billing_amount=$_POST["amount"];

$success=0;
$status="Active";


		
$query="INSERT INTO orders_entry (order_id,restaurant_id,user_id,address,order_detail,billing_amount) VALUES ('".$_POST["orderid"]."','".$_POST["restid"]."','".$_POST["uid"]."','".$_POST["add"]."','".$_POST["orderdetail"]."','".$_POST["amount"]."')";

mysqli_query($conn,$query) ;
mysqli_close($conn);
?>
