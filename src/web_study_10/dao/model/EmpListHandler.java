package web_study_10.dao.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import web_study_10.dto.Employee;
import web_study_10.service.EmployeeService;


@WebServlet("/EmpListHandler")
public class EmpListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service;


	public void init(ServletConfig config) throws ServletException {
		service = new EmployeeService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			List<Employee> list = service.selectEmployeeByAll();
			request.setAttribute("list", list);
			request.getRequestDispatcher("empList.jsp").forward(request, response);
		}else {
			System.out.println("POST");
			List<Employee> list = service.selectEmployeeByAll();
			
			
			Gson gson = new Gson();
			String result = gson.toJson(list, new TypeToken<List<Employee>>(){}.getType());
			System.out.println(result);
			
			response.setContentType("Application/json");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
		}
		
	}

}
