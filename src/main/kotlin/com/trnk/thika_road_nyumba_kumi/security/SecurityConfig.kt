package com.trnk.thika_road_nyumba_kumi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.invoke
//import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.provisioning.JdbcUserDetailsManager
//import org.springframework.security.provisioning.UserDetailsManager
//import org.springframework.security.web.SecurityFilterChain
//import javax.sql.DataSource


@Configuration
//@EnableWebSecurity
class SecurityConfig{
    //password encoder
    @Bean
    fun bCryptPasswordEncoder():BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    //spring Security
//    @Bean
//     fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.invoke {
//            authorizeRequests {
//
//                authorize("/**",permitAll)
//                authorize("/user/**",permitAll)
//                authorize(anyRequest, authenticated)
//
//            }
//            formLogin { }
//            httpBasic { }
//
//        }
//        return http.build()
//    }

//    @Bean
//    fun users(dataSource: DataSource): UserDetailsManager {
//        return  JdbcUserDetailsManager(dataSource)
//        val user = User.builder()
//            .username("user")
//            .password("{bcrypt}$2a$10\$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//            .roles("USER")
//            .build();
//        val admin = User.builder()
//            .username("admin")
//            .password("{bcrypt}$2a$10\$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//            .roles("USER", "ADMIN")
//            .build();
//        val users = JdbcUserDetailsManager(dataSource)
//        users.createUser(user)
//        users.createUser(admin)
//        return users
//    }

}