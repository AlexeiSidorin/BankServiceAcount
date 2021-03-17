
import com.github.javafaker.Faker;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {


    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }


    @Test
    void shouldSuccessLogin() {
        val validUser = DataGenerator.getRegisteredUser("active");
        $("[type='text']").setValue(validUser.getLogin());
        $("[type='password']").setValue(validUser.getPassword());
        $(".button__text").click();
        $(withText("Личный кабинет")).shouldBe(visible);
    }

    @Test
     void shouldReturnMessageUserBlocked() {
        val invalidUser = DataGenerator.getUserBlocked("blocked");
        $("[type='text']").setValue(invalidUser.getLogin());
        $("[type='password']").setValue(invalidUser.getPassword());
        $(".button__text").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    void shouldGetErrorIfIncorrectPassword() {
        val wrongPassUser = DataGenerator.getWrongPasswordUser();
        $("[type='text']").setValue(wrongPassUser.getLogin());
        $("[type='password']").setValue(wrongPassUser.getPassword());
        $(byText("Продолжить")).click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldGetErrorIfIncorrectLogin() {
        val wrongLoginUser = DataGenerator.getWrongLoginUser();
        $("[type='text']").setValue(wrongLoginUser.getLogin());
        $("[type='password']").setValue(wrongLoginUser.getPassword());
        $(byText("Продолжить")).click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }


}
