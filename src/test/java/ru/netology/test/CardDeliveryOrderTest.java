package ru.netology.test;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.entities.RegistrationInfo;
import ru.netology.utils.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.utils.DataGenerator.Registration.generateDate;

public class CardDeliveryOrderTest {

    @Test
    public void shouldSuccessfulPlanAndReplanMeeting() {
    RegistrationInfo info = DataGenerator
            .Registration
            .generateInfo("ru");

    String planningDateBefore = generateDate(3);
    String planningDateAfter = generateDate(5);

    open("http://localhost:9999/");
    $("[data-test-id=city] input").setValue(info.getCity());
    $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    $("[data-test-id=date] input").setValue(planningDateBefore);
    $("[data-test-id=name] input").setValue(info.getName());
    $("[data-test-id=phone] input").setValue(info.getPhoneNumber());
    $("[data-test-id=agreement] span").click();
    $(withText("Запланировать")).click();
    $("[data-test-id=success-notification]").shouldBe(visible);
    $(".notification__title").shouldHave(text("Успешно!"));
    $(".notification__content").shouldHave(text("Встреча успешно запланирована на " + planningDateBefore));
    $("[data-test-id=success-notification] button").click();
    $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    $("[data-test-id=date] input").setValue(planningDateAfter);
    $(withText("Запланировать")).click();
    $("[data-test-id=replan-notification]").shouldBe(visible);
    $$(".notification__title").findBy(text("Необходимо подтверждение")).should(exist);
    $$(".notification__content").findBy(text("У вас уже запланирована встреча на другую дату. Перепланировать?")).should(exist);
    $("[data-test-id=replan-notification] button").click();
    $("[data-test-id=success-notification]").shouldBe(visible);
    $(".notification__title").shouldHave(text("Успешно!"));
    $(".notification__content").shouldHave(text("Встреча успешно запланирована на " + planningDateAfter));
}
}
