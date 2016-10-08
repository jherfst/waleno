package co.waleno;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
//@ComponentScan(basePackages ={"com.chamberofcommerce.shared","sr.qualogy.qforms"})
public class Application  extends SpringBootServletInitializer {
	
	/* @Value("${spring.activemq.broker-url}")
	private  String brokerUrl;*/
	 
	 
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
  
/*    @Bean
    JmsListenerContainerFactory<?> jmsContainerFactory(PooledConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        connectionFactory.setIdleTimeout(8000000);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }*/

    
  /*  @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
    	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
    	factory.setTrustedPackages(Arrays.asList("sx.chamberofcommerce.shared.business.core.messaging"));
        return factory;
    }
    */
    
    @Bean
    public HttpSessionListener httpSessionListener(){
        return new AppSessionListener();
    }
    
    class AppSessionListener implements HttpSessionListener {
    	 
        @Override
        public void sessionCreated(HttpSessionEvent event) {
            System.out.println("==== Session is created ====");
            synchronized (this) {
            	  event.getSession().setMaxInactiveInterval(-1);
                ServletContext application = event.getSession().getServletContext();
               Integer sessionCount = (Integer) application.getAttribute("SESSION_COUNT");
                if (sessionCount == null) {
                    application.setAttribute("SESSION_COUNT", (sessionCount = 1));//setting sessioncount inside application scope
                } else {
                    application.setAttribute("SESSION_COUNT", ++sessionCount );
                }
                System.out.println("Session Created1: "+ event.getSession().getId());
                System.out.println("Total Sessions1: " + sessionCount);
            }
        }
     
        @Override
        public void sessionDestroyed(HttpSessionEvent event) {
        	   int sessionCount = 0;
        	   synchronized (this) {
        	        ServletContext application = event.getSession().getServletContext();
        	         sessionCount = (Integer) application.getAttribute("SESSION_COUNT");
        	        application.setAttribute("SESSION_COUNT", --sessionCount );
        	    }
        	    System.out.println("Session Destroyed: " + event.getSession().getId());
        	    System.out.println("Total Sessions: " + sessionCount);
        }
    }
    
   
}
