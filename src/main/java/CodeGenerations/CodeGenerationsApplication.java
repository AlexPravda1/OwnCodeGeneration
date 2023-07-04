package CodeGenerations;

import ann.Handleable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeGenerationsApplication extends CodeGenerations.CodeGenerationsApplicationExtras {

	@Handleable
	private String stuff;

	public static void main(String[] args) {
		new CodeGenerationsApplication().handleStuff("hello sdsdsd");
		SpringApplication.run(CodeGenerationsApplication.class, args);
	}
	}
