package ru.starovoytov.servlets;

import ru.starovoytov.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет для отображения таблицы с рассчётами
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class CreditTableServlet extends HttpServlet {
	private static final String TABLE_PAGE = "templates/calcTable.html";

	@Override
	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		String amount = request.getParameter("amount");
		String count = request.getParameter("count");
		String rate = request.getParameter("rate");

		response.getWriter()
			.println(PageGenerator.instance().getPage(TABLE_PAGE, new HashMap<>()));
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
