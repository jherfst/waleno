package co.waleno.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;


  @Configuration
  public class RepositoryConfiguration extends RepositoryRestMvcConfiguration {
	 
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
       // config.exposeIdsFor(Subject.class,Requestor.class);
    }
}