package com.lajil.mvc.servlet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.internal.matchers.SubstringMatcher;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.lajil.mvc.dao.CriteriaCustomer;
import com.lajil.mvc.dao.CustomerDAO;
import com.lajil.mvc.domain.Customer;
import com.lajil.mvc.impl.CustomerDAOJdbcImpl;
import com.mysql.jdbc.ReplicationMySQLConnection;



public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String method = request.getParameter("method");
//		
//		switch (method) {
//		case "add":add(request,response);
//		break;
//		case "query":query(request,response);
//		break;
//		case "delete":delete(request,response);
//		break;
//		}
//	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);

		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e){
			resp.sendRedirect("error.jsp");
			//e.printStackTrace();
		}
	}
	
	private void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String forwardPath = "/error.jsp";
		String idstr = request.getParameter("id");
		int id = -1;
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idstr));
			if(customer != null){
				forwardPath = "/updatecustomer.jsp";
				request.setAttribute("customer", customer);
			}
		} catch (NumberFormatException e) {
		}
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	
	private void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String oldName = request.getParameter("oldName");
		
		if (!oldName.equals(name)) {
			long count = customerDAO.getCountWithName(name);
			if (count > 0) {
				request.setAttribute("message", "username " + name + " has been existed");
				
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}
		}
		Customer customer = new Customer(name,address,phone);
		customer.setId(Integer.parseInt(id));
		
		customerDAO.update(customer);
		response.sendRedirect("query.do");
	}
	
	
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idstr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idstr);
			customerDAO.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			response.sendRedirect("query.do");
		}
		
	}


	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		//调用CustomerDAO的getAll()得到Customer集合
		
		List<Customer> customers = customerDAO.getForListCriteriaCustomer(cc);
		//把Customer的集合放入request中
		request.setAttribute("customers", customers);
		//转发页面到index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone"); 
		
		//判断NAME 是否重复
		long count = customerDAO.getCountWithName(name);
		if (count > 0) {
			request.setAttribute("message", "username " + name + " has been existed");
			request.getRequestDispatcher("/newCustomer.jsp").forward(request, response);
			return;
		}
		Customer customer = new Customer(name,address,phone);
		customerDAO.save(customer);
		
		
		response.sendRedirect("success.jsp");
	}

}
