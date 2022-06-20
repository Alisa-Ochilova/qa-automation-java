package API;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.*;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiDemoTest extends Country {

    private static Connection connection;

    @BeforeAll
    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @AfterAll
    public static void disconnect() throws SQLException, IOException {
        connection.close();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        setCountryName(randomAlphabetic(2));
 //       setId(random.nextInt(1000));

        PreparedStatement sql = connection.prepareStatement(
                "INSERT INTO country(country_name) VALUES( ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        sql.setString(1, getCountryName());
        sql.executeUpdate();

        ResultSet keys = sql.getGeneratedKeys();
        assertThat(keys.next(), is(true));
        setId(keys.getInt("id"));
    }

    @AfterEach
    public void setDown() throws SQLException {
        PreparedStatement sql = connection.prepareStatement(
                "DELETE FROM country WHERE country_name = ?"
        );
        sql.setString(1, getCountryName());
        sql.executeUpdate();
    }

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void shouldCreateCountriesDb() throws SQLException {
        setDown();
        given()
                .contentType("application/json")
                .body(
                        "{\n" +
                                "  \"countryName\": \"" + getCountryName() + "\"\n" +
                                "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()),
                        "countryName", is(getCountryName()),
                        "location", nullValue());

        Collection<String> countryNames = new ArrayList<>();

        Statement sql = connection.createStatement();
        ResultSet resultSet = sql.executeQuery("SELECT * FROM country");
        while (resultSet.next()) {
            countryNames.add(resultSet.getString(2));
        }
        assertThat(countryNames, hasItem(getCountryName()));
    }

    @Test
    public void shouldGetCountry() {
        when()
                .get("/api/countries/" + getId())
                .then()
                .statusCode(200)
                .body(
                        "id", is(getId()),
                        "countryName", is(getCountryName()),
                        "location", nullValue()
                );
    }

    @Test
    public void shouldDeleteCountry() {
        when()
                .delete("/api/countries/" + getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldPatchCountry() {
        given()
                .contentType("application/json")
                .body(
                        "{\n" +
                                "  \"id\": \"" + getId() + "\"\n" +
                                "," +
                                "  \"countryName\": \"" + getCountryName() + "\"\n" +
                                "}")
                .when()
                .patch("/api/countries/" + getId())
                .then()
                .statusCode(200)
                .body("id", is(getId()),
                        "countryName", is(getCountryName()),
                        "location", nullValue());
    }
}
