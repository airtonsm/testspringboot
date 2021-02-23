package curso.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Override //configura as solicitações de acesso por http
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable() 	// ------------------------------------ Desativa as configurações padrão de memória
		.authorizeRequests() //---------------------------- Permitir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() //----qualquer usuário acessa
		.anyRequest().authenticated() 	//------------------criar autenticação de login
		.and().formLogin().permitAll() 	//------------------permite qualquer usuário logar
		.and().logout() 	//------------------------------Mapeia URL de Logout e invalida usuário autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override // cria autenticação do usuário com o banco ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("airton")
		.password("$2a$10$IMRdE.TTV5gMkiOnZDQKl.VdxOfoZy6F7eW.demLk3jZsBWJWFfe6")
		.roles("ADMIN");
	}
	
	@Override // ignora URL especificas (
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");
	}

}
