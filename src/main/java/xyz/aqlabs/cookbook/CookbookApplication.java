package xyz.aqlabs.cookbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class CookbookApplication {

	public static void main(String[] args) {
		String key = System.getenv("OPENAI_API_KEY");

		if (key != null && !key.isEmpty()) {
			SpringApplication.run(CookbookApplication.class, args);
		} else {
			System.out.println("API KEY WAS NOT FOUND IN ENVIRONMENT. APPLICATION NOT STARTED.");
			System.exit(1);
		}
	}
}