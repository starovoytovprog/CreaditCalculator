package ru.starovoytov.servlets;

import ru.starovoytov.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет, отображающий "Hello world!"
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class HelloWorldServlet extends HttpServlet {
	private static final String HELLO_WORLD_PAGE = "templates/helloWorldPage.html";

	@Override
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		Map<String, Object> pageVariables = createPageVariablesMap(request);

		response.getWriter()
			.println(PageGenerator.instance().getPage(HELLO_WORLD_PAGE, pageVariables));
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
		Map<String, Object> pageVariables = new HashMap<>();
		pageVariables.put("URL", request.getRequestURL().toString());
		return pageVariables;
	}
}
