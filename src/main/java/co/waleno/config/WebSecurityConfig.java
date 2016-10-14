package co.waleno.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class WebSecurityConfig {
	
	@Component   
	@EnableOAuth2Sso
	public static class LoginConfigurer extends	WebSecurityConfigurerAdapter {
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
		
		}
		
        protected static class CORSFilter implements Filter {
	        public void init(FilterConfig filterConfig) throws ServletException { }
	        public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)  throws IOException, ServletException {
	            HttpServletResponse response = (HttpServletResponse) res;
	            response.setHeader("Access-Control-Allow-Origin", "*");
	            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	            response.setHeader("Access-Control-Max-Age", "3600");
	            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	            chain.doFilter(request, response);
	        }
	        public void destroy() {}
	    }

		private Filter csrfHeaderFilter() {
			return new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest request,	HttpServletResponse response, FilterChain filterChain)	throws ServletException, IOException {
					CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
					if (csrf != null) {
						Cookie cookie = new Cookie("XSRF-TOKEN",csrf.getToken());
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					filterChain.doFilter(request, response);
				}
			};
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}
	}
}
