package ru.starovoytov.servlets;

import ru.starovoytov.PageGenerator;
import ru.starovoytov.calc.Calculator;
import ru.starovoytov.calc.Row;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

		int amount = Integer.parseInt(request.getParameter("amount"));
		int count = Integer.parseInt(request.getParameter("count"));
		double rate = Double.parseDouble(request.getParameter("rate"));

		Calculator calculator = new Calculator(amount, count, rate);

		Map<String, Object> params = new HashMap<>();

		List<Row> rows = calculator.getPaySchedule();

		params.put("rows", rows);
		response.getWriter()
			.println(PageGenerator.instance().getPage(TABLE_PAGE, params));
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
