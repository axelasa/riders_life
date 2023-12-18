import com.trnk.thika_road_nyumba_kumi.config.AuthorizationFilter
import com.trnk.thika_road_nyumba_kumi.config.BasicFilter
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {


    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity,
                            authEntryPoint: AuthenticationEntryPoint,
                            authenticationManager: AuthenticationManager,
                            basicFilter: BasicFilter, authorizationFilter: AuthorizationFilter
    ): SecurityFilterChain {


        httpSecurity
            .csrf().disable()
            .httpBasic()
            .and()
            .addFilterBefore(basicFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authorizationFilter, BasicAuthenticationFilter::class.java)
            .authenticationManager(authenticationManager)
            .authorizeHttpRequests {
                it.mvcMatchers("/trnk/sign_in/**").authenticated()
                it.mvcMatchers("/user/new_user", "/user/search_user",).permitAll()
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }


        return httpSecurity.build()
    }
}