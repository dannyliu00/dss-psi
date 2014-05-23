package com.polaris.psi.filter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggingServlet extends HttpServlet {

	private static final long serialVersionUID = -6109922348005865235L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggerParam = req.getParameter("logger");
        String levelParam = req.getParameter("loggingLevel");

        Logger logger = Logger.getLogger(loggerParam);
        Level level = Level.toLevel(levelParam);
        logger.setLevel(level);

        resp.sendRedirect("logging.jsp");
    }
}
