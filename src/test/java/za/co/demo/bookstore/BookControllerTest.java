package za.co.demo.bookstore;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import za.co.demo.bookstore.domain.dto.BookDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class BookControllerTest {
    @Autowired
    private DataSource dataSource;
    @Container
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("bookstore")
            .withUsername("test")
            .withPassword("test")
            .waitingFor(Wait.forListeningPort())
            .withEnv("MYSQL_ROOT_HOST", "%");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
    }
    @AfterEach
    void tearDown() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).close();
        }
    }
    @Test
    public void testCreateUser() {
        BookDto bookDto = BookDto.builder()
                .isbn("9780131492028")
                .title("Java: An Introduction to Problem Solving and Programming")
                .author("Robert M. Price")
                .publisher("Prentice Hall")
                .publicationDate(LocalDate.parse("2004-12-01"))
                .quantityInStock(5)
                .pages(650)
                .price(BigDecimal.valueOf(650.00))
                .build();

        BookDto response = restTemplate.postForObject("http://localhost:" + port + "/users", bookDto, BookDto.class);
        assertThat(response).isNotNull();
    }
}
