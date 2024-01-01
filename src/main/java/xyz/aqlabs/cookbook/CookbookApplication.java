package xyz.aqlabs.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CookbookApplication {
	public static void main(String[] args) {
		String key = System.getenv("KEY");
		if (key != null && !key.isEmpty()) {
			System.out.println("KEY was found with value: " + key );
			SpringApplication.run(CookbookApplication.class, args);
		} else {
			System.out.println("API KEY WAS NOT FOUND IN ENVIRONMENT. APPLICATION NOT STARTED.");
			System.exit(1);
		}
	}
}