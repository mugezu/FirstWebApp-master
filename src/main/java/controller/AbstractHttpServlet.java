package controller;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;

/**
 * Created by Роман on 19.06.2017.
 */
public abstract class AbstractHttpServlet extends HttpServlet{
    protected final Logger log = Logger.getLogger(this.getClass());
}
