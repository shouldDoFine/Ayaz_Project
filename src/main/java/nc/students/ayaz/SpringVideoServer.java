package nc.students.ayaz;

import cobertura.IgnoreDuringCodeCoverage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringVideoServer {

    @IgnoreDuringCodeCoverage
    public static void main(String[] args) {
        SpringApplication.run(SpringVideoServer.class, args);
    }
}
