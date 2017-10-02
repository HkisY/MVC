<%@ page language="java"
	 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.lajil.mvc.domain.Customer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Object msg = request.getAttribute("message");
		if(msg != null){
	%>
		<br>
		<font color="red"><%=msg %></font>
		<br>
		<br>
	<% 
		}
		
		String id = null;
		String name = null;
		String oldName = null;
		String address = null;
		String phone = null;
		Customer customer = (Customer)request.getAttribute("customer");
		if(customer != null){
			id = customer.getId() + "";
			name = customer.getName();
			phone = customer.getPhone();
			address = customer.getAddress();
			oldName = customer.getName();
		} else {
			id = request.getParameter("id");
			name = request.getParameter("oldName");
			phone = request.getParameter("phone");
			address = request.getParameter("address");
			oldName = request.getParameter("oldName");
			
		}
	%> 
	
	<form action = "update.do" method="post">
		
		<input type="hidden" name = "id" value = "<%=id%>"/>
		<input type="hidden" name = "oldName" value = "<%=oldName%>"/>
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type = "text" name="name" 
					value="<%=name %>"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type = "text" name="address" 
					value="<%=address%>"/></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type = "text" name="phone"  
					value="<%=phone%>"/></td>
			</tr>
			<tr>
				<td colspan ="2"><input type ="submit" value="Submit"></td>
				
			</tr>
		</table>
	</form>
</body>
</html>