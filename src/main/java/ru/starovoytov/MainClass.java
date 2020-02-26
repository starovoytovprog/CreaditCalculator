package ru.starovoytov;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.starovoytov.servlets.CreditTableServlet;
import ru.starovoytov.servlets.HelloWorldServlet;
import ru.starovoytov.servlets.IndexServlet;

/**
 * Стартовый класс
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class MainClass {
	private static final Server server = new Server(8080);

	public static void main(String[] args) throws Exception {
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

		contextHandler.addServlet(new ServletHolder(new HelloWorldServlet()), "/hello");
		contextHandler.addServlet(new ServletHolder(new IndexServlet()), "/index");
		contextHandler.addServlet(new ServletHolder(new CreditTableServlet()), "/table");

		server.setHandler(contextHandler);
		server.start();
	}
}
