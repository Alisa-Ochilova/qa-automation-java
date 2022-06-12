package API;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.*;

public class ApiDemoTest {
    String countryName = randomAlphabetic(2);

    Random random = new Random();
    int id = random.nextInt(1000);
    String idString = String.valueOf(id);


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
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"" + countryName + "\"\n" +
                        "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()),
                        "countryName", is(countryName),
                        "location", nullValue())
                .extract()
                .response()
                .path(idString = "id");
        when().delete("/api/countries/" + id);
    }

    @Test
    public void shouldGetCountry() {
        id = given()
                .body("{\n" +
                        "  \"countryName\": \"" + countryName + "\"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/api/countries")
                .body()
                .path("id");
        when()
                .get("/api/countries/" + id)
                .then()
                .statusCode(200)
                .body(
                        "id", is(id),
                        "countryName", is(countryName),
                        "location", nullValue()
                );
        when().delete("/api/countries/" + id);
    }

    @Test
    public void shouldDeleteCountry() {
        id = given()
                .body("{\n" +
                        "  \"countryName\": \"" + countryName + "\"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/api/countries")
                .body()
                .path("id");
        when()
                .delete("/api/countries/" + id)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldPatchCountry() {
        id = given()
                .body("{\n" +
                        "  \"countryName\": \"" + countryName + "\"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/api/countries")
                .body()
                .path("id");
        given()
                .contentType("application/json")
                .body(
                        "{\n" +
                                "  \"id\": \"" + id + "\"\n" +
                                "," +
                                "  \"countryName\": \"" + countryName + "\"\n" +
                                "}")
                .when()
                .patch("/api/countries/" + id)
                .then()
                .statusCode(200)
                .body("id", is(id),
                        "countryName", is(countryName),
                        "location", nullValue());
        when().delete("/api/countries/" + id);
    }
}
