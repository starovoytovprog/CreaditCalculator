package ru.starovoytov;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.starovoytov.servlets.HelloWorldServlet;

/**
 * Стартовый класс
 *
 * @author anton_starovoytov
 * @since 2020.02.26
 */
public class MainClass {
	private static final Server server = new Server(8080);

	public static void main(String[] args) throws Exception {
		HelloWorldServlet helloWorldServlet = new HelloWorldServlet();

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(helloWorldServlet), "/hello");

		server.setHandler(contextHandler);
		server.start();
	}
}
