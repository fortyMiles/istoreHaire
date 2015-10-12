package com.istore.common.web.util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SessionListener implements HttpSessionListener
{
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent arg0)
    {
        logger.info("============================sessionCreated");
    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
        session.invalidate();
    }

}
