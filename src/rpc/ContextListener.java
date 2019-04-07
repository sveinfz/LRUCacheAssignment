package rpc;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Starting up!");
        //Initialize cache capacity
        LRUCache cache = new LRUCache(2);
        event.getServletContext().setAttribute("cache", cache);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Shutting down!");
    }
}