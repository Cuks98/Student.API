package hr.tvz.curin.studapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StudappApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =SpringApplication.run(StudappApplication.class, args);
        //Object dataSource = context.getBean("dataSource");
        //System.out.println(dataSource);
    }

}
