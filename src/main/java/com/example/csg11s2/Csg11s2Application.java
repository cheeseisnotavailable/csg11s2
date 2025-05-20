package com.example.csg11s2;

import com.example.csg11s2.framework.Page;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import static com.example.csg11s2.util.FileOperations.addPreexistingPagesFromMenu;
import static com.example.csg11s2.util.FileOperations.readFile;

@SpringBootApplication
public class Csg11s2Application {

	public static void main(String[] args) {
//		addPreexistingPagesFromMenu();
//		String contents = "I +blike+ cheese and bagels \nI +ilike+ +dcheese+ \nI +ulike+ cheese";
//		String contents2 = "I +blike+ cheese, +bnot+ bagels\nI +ihate+ +bbagels+ \nI +uhate+ bagels";
//		Page bagelsPage = new Page("bagels", contents);
//		Page cheesePage = new Page("cheese", contents2);
//		Page goosePage = new Page("goose", "honk goes the goose \nhonk goes the goose \nhonk goes the goose");
//		Page dollsHouse = new Page("dollshouse", readFile("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/sourcetext/dollshouse.txt"));

		SpringApplication.run(Csg11s2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("THE APPLICATION HAS BEGUN!");

		};
	}

}
