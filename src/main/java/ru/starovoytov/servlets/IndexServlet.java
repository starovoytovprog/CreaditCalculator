package ru.starovoytov.servlets;

import ru.starovoytov.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет для стартовой страницы калькулятора
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class IndexServlet extends HttpServlet {
	private static final String INDEX_PAGE = "templates/index.html";

	@Override
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		double rate = ((int) ((Math.random() * 100_000) % 110 + 129)) / 10.0;

		Map<String, Object> params = new HashMap<>();
		params.put("rate", Double.toString(rate));

		response.getWriter()
			.println(PageGenerator.instance().getPage(INDEX_PAGE, params));
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
