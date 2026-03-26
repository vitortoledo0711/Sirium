
package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "controller",
        "service",
        "repository",
        "security",
        "model"
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiApplication.class);
        var ctx = app.run(args);
        var env = ctx.getEnvironment();
        System.out.println("[DEBUG] spring.datasource.password='" + env.getProperty("spring.datasource.password") + "'");
    }

}
