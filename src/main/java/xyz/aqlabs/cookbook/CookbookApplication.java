package xyz.aqlabs.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class CookbookApplication {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter API Key to Launch Application");
		try {
			var apiKey = scan.nextLine();
			System.setProperty("openai.api.key", apiKey);
			SpringApplication.run(CookbookApplication.class);
		} catch (Exception e){
			System.err.println("Did not provide API Key");
			main(args);
		}
	}
}
