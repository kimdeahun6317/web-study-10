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

import web_study_10.dto.Department;
import web_study_10.service.DepartmentService;

/**
 * Servlet implementation class DeptAddHandler
 */
@WebServlet("/DeptAddHandler")
public class DeptAddHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentService service;

	public void init(ServletConfig config) throws ServletException {
		service = new DepartmentService();
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
			int nextNo = service.getNextNo();
			System.out.println("nextNo>" + nextNo);

			response.getWriter().print(nextNo);
			/*
			 * request.setAttribute("nextNo", nextNo);
			 * request.getRequestDispatcher("titleAdd.jsp").forward(request, response);
			 */

		} else {
			System.out.println("POST");
			// {titleNo:6 titleName:"인턴"} => title객체

			Gson gson = new Gson();
			Department newDept = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"),
					Department.class);
			System.out.println(newDept);

			int res = service.addDept(newDept);

			response.getWriter().print(res);

		}
	}

}
