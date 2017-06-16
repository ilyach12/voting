package com.chaikovsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource("classpath:properties/query.properties")
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

	@Autowired
	private void setTemplate(JdbcTemplate template){
		this.template = template;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
