package com.example.csg11s2;

import com.example.csg11s2.framework.Page;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import static com.example.csg11s2.util.FileOperations.*;

@SpringBootApplication
public class Csg11s2Application {

	public static void main(String[] args) {
//		resetProject();
//		addPreexistingPagesFromMenu();
//		String contents = "I ~b like~ cheese and bagels ^I ~i like+ ~d cheese~ ^I ~u like~ cheese";
//		String contents2 = "I ~b like~ cheese, ~b not~ bagels^I ~ihate~ ~b bagels~ ^I ~u hate~ bagels";
//		Page bagelsPage = new Page("bagels", contents);
//		Page cheesePage = new Page("cheese", contents2);
//		Page dollsHouse = new Page("dollshouse", readFile("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/sourcetext/dollshouse.txt"));

		SpringApplication.run(Csg11s2Application.class, args);

//		SpringApplication.;
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("THE APPLICATION HAS BEGUN!");

		};
	}

}
