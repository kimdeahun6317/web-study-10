package web_study_10.dao.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.glass.ui.Application;

import web_study_10.dto.Title;
import web_study_10.service.TitleService;

@WebServlet("/TitleListHandler")
public class TitleListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TitleService service;

	public void init(ServletConfig config) throws ServletException {
		service = new TitleService();
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
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			List<Title> list = service.showTitle();
			request.setAttribute("list", list);
			request.getRequestDispatcher("titleList.jsp").forward(request, response);

		} else {
			System.out.println("POST");
			List<Title> list = service.showTitle();

			
			Gson gson = new Gson();
			String result = gson.toJson(list, new TypeToken<List<Title>>(){}.getType());
			System.out.println(result);
			
			response.setContentType("Application/json");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();

		}
	}

}
