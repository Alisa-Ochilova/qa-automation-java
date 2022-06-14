package API;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.*;

public class ApiDemoTest extends Country{
    @BeforeEach
    public void setUp() {
        setCountryName(randomAlphabetic(2));
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
    public void shouldCreateCountriesDb() {
        setId(given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"" + getCountryName() + "\"\n" +
                        "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()),
                        "countryName", is(getCountryName()),
                        "location", nullValue())
                .extract()
                .response()
                .path("id"));
        when().delete("/api/countries/" + getId());
    }

    @Test
    public void shouldGetCountry() {
        setId(given()
                .body("{\n" +
                        "  \"countryName\": \"" + getCountryName() + "\"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/api/countries")
                .body()
                .path("id"));
        when()
                .get("/api/countries/" + getId())
                .then()
                .statusCode(200)
                .body(
                        "id", is(getId()),
                        "countryName", is(getCountryName()),
                        "location", nullValue()
                );
        when().delete("/api/countries/" + getId());
    }

    @Test
    public void shouldDeleteCountry() {
        setId(given()
                .body("{\n" +
                        "  \"countryName\": \"" + getCountryName() + "\"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/api/countries")
                .body()
                .path("id"));
        when()
                .delete("/api/countries/" + getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldPatchCountry() {
        setId(given()
                .body("{\n" +
                        "  \"countryName\": \"" + getCountryName() + "\"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/api/countries")
                .body()
                .path("id"));
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
        when().delete("/api/countries/" + getId());
    }
}
