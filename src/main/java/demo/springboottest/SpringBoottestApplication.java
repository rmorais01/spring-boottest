package demo.springboottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Application.
 *
 * @author rmorais
 */

@SpringBootApplication
public class SpringBoottestApplication {

    /**
     * Application entry point.
     *
     * @param args
     *            The program arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBoottestApplication.class, args);
    }
}
