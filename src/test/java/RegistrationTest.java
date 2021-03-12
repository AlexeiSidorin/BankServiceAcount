
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    RegistrationDto user;
    Faker faker;

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
        faker = new Faker(new Locale("eng"));
        user = new RegistrationDto();

    }


    @Test
    void shouldActiveUser() {
        AuthTest.setActiveUser();
        $("[type='text']").setValue(user.getLogin());
        $("[type='password']").setValue(user.getPassword());
        $(".button__text").click();
        $(withText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldBlockedUser() {
        AuthTest.setBlockedUser();
        $("[type='text']").setValue(user.getLogin());
        $("[type='password']").setValue(user.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible);

    }

    @Test
    void shouldIncorrectPassword() {
        AuthTest.setIncorrectPassword();
        $("[type='text']").setValue(user.getLogin());
        $("[type='password']").setValue(faker.internet().password());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible);

    }

    @Test
    void shouldIncorrectLogin() {
        AuthTest.setIncorrectLogin();
        $("[type='text']").setValue(faker.name().fullName());
        $("[type='password']").setValue(user.getPassword());
        $(byText("Продолжить")).click();
       $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(4));

    }



}
