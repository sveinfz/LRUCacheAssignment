package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rpc.ContextListener;
import rpc.LRUCache;

@RunWith(MockitoJUnitRunner.class)
class ContextListenerTest {
	@Mock
    ServletContextEvent mockEvent;
	@Mock
    ServletContext mockServletContext;
    @Mock
    LRUCache mockCache;
    
	@Test
	void testContextInitialized() {
		Mockito.when(mockEvent.getServletContext()).thenReturn(mockServletContext);
		Mockito.when(mockServletContext.getAttribute(Matchers.anyString())).thenReturn(mockCache);
        ContextListener listener = new ContextListener();
        listener.contextInitialized(mockEvent);
        
        Mockito.verify(mockEvent, Mockito.times(1)).getServletContext();
	}

}
