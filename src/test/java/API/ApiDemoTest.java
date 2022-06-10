package API;

import API.Dto.CountryPojo;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ApiDemoTest extends CountryPojo {
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
    public void shouldGetCountriesDb() {
        when()
                .get("/api/countries")
                .then()
                .statusCode(200)
                .body(
                        "size()", is(12),
                        "[0].countryName", is("vi")
                );
    }

    @Test
    public void shouldGetCountry() {
        when()
                .get("/api/countries/3")
                .then()
                .statusCode(200)
                .body(
                        "id", is(3),
                        "countryName", is("St"),
                        "location", nullValue()
                );
    }

    @Test
    public void shouldCreateCountryWhenUniqu() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"CT\"\n" +
                        "}")
                .when()
                .post("/api/countries/")
                .then()
                .statusCode(201)
                .body("id", not(empty()),
                        "countryName", is("CT"),
                        "location", nullValue());
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
                .body("{\n" +
                        "  \"id\": 10,\n" +
                        "  \"countryName\": \"de\"\n" +
                        "}")
                .when()
                .patch("/api/countries/10")
                .then()
                .statusCode(200)
                .body("id", not(empty()),
                        "countryName", is("de"),
                        "location", nullValue());
    }
}
