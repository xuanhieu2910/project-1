package vuonghieu.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjectApplication {




    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }


}
