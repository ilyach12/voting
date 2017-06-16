package com.chaikovsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@PropertySource("classpath:db/query.properties")
public class VotingApplication implements CommandLineRunner {

	private JdbcTemplate template;
	@Value("${schema}")
	private String schema;
	@Value("${dummyData}")
	private String data;

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		template.execute(schema);
		template.execute(data);
	}

	/*@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return container -> container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
	}*/

	@Autowired
	private void setTemplate(JdbcTemplate template){
		this.template = template;
	}
}
