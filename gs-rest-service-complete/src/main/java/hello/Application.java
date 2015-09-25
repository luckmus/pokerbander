package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {
	public static ClassPathXmlApplicationContext ac = null;
    public static void main(String[] args) {
    	ac = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        SpringApplication.run(Application.class, args);
    }
}
