package xyz.aqlabs.cookbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class CookbookApplication {

	@Autowired
	static Environment env;


	public static void main(String[] args) {

		String key = env.getProperty("OPENAI_API_KEY");

		try{
			if(!key.isEmpty())
				SpringApplication.run(CookbookApplication.class);
			else
				System.out.print("API KEY WAS NOT FOUND IN ENVIRONMENT");
		}catch (Exception e){
			System.err.println("Api Key Check resulted in error: " + e.getMessage());
		}
	}
}