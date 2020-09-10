package web_study_10.dao.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import web_study_10.dto.Employee;
import web_study_10.service.EmployeeService;

@WebServlet("/EmpModifyHandler")
public class EmpModifyHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service;

	public void init(ServletConfig config) throws ServletException {
		service = new EmployeeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
		} else {
			System.out.println("EmpModifyHandler POST");

			Gson gson = new Gson();
			Employee updateEmp = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"), Employee.class);
			System.out.println(updateEmp);
			
			int res = service.updateEmployee(updateEmp);
			response.getWriter().print(res);
		}

	}

}
