package testREST.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import testREST.DBService.UserDetailsServiceImpl;

/**
 * Created by stepa on 29.06.15.
 */


@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private FailAuthHandler failAuthHandler;

    @Autowired
    private SuccessAuthHandler successAuthHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .and()
                .userDetailsService(userDetailsServiceImpl)
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .formLogin()
                .loginPage("/auth")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successAuthHandler)
                .failureHandler(failAuthHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new TokenFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/auth").permitAll();

    }
}
