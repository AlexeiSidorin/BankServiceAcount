import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    RegistrationDto activeUser;

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");

    }


    @Test
    void shouldActiveUser() {
        $("[type='text']").setValue(AuthTest.setActiveUser().getLogin());
        $("[type='password']").setValue(AuthTest.setActiveUser().getPassword());
        $(".button__text").click();
        $(withText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldBlockedUser() {
        $("[type='text']").setValue(AuthTest.setBlockedUser().getLogin());
        $("[type='password']").setValue(AuthTest.setBlockedUser().getPassword());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible);

    }

    @Test
    void shouldIncorrectPassword() {
        $("[type='text']").setValue(AuthTest.setIncorrectPassword().getLogin());
        $("[type='password']").setValue(AuthTest.setIncorrectPassword().getPassword());
        $(byText("Продолжить")).click();
        $(withText("Ошибка")).shouldBe(visible);

    }

    @Test
    void shouldIncorrectLogin() {
        $("[type='text']").setValue(AuthTest.setIncorrectLogin().getLogin());
        $("[type='password']").setValue(AuthTest.setIncorrectLogin().getPassword());
        $(byText("Продолжить")).click();
       $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(4));

    }



}
