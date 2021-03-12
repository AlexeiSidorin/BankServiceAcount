import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class AuthTest {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void dataAccess(RegistrationDto registration) {
        RestAssured.given()
                .spec(requestSpecification)
                .body(registration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }


    public static RegistrationDto setActiveUser() {
        String login = "vasya";
        String password = "password";
        String status = "active";
        RegistrationDto registration = new RegistrationDto(login, password, status);
        dataAccess(registration);
        return registration;
    }

    public static RegistrationDto setBlockedUser() {
        String login = "vasya";
        String password = "password";
        String status = "blocked";
        RegistrationDto registration = new RegistrationDto(login, password, status);
        dataAccess(registration);
        return registration;
    }

    public static RegistrationDto setIncorrectPassword() {
        String login = "vasya";
        String password = "qwert";
        String status = "active";
        RegistrationDto registration = new RegistrationDto(login, password, status);
        dataAccess(registration);
        return registration;
    }

    public static RegistrationDto setIncorrectLogin() {
        String login = "dasha";
        String password = "password";
        String status = "active";
        RegistrationDto registration = new RegistrationDto(login, password, status);
        dataAccess(registration);
        return registration;
    }



}
