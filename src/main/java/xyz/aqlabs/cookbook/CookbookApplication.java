package xyz.aqlabs.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CookbookApplication {
	public static void main(String[] args) {

		// you must Include the api key as an argument when launching the jar
		if (args.length > 0) {
			String apiKey = args[0];
			System.setProperty("openai.api.key", apiKey);

			SpringApplication.run(CookbookApplication.class, Arrays.copyOfRange(args, 1, args.length));
		} else {
			System.out.println("Please provide your OpenAI API key as a command-line argument.");
		}
	}
}
