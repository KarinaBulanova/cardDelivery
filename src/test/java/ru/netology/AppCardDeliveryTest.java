package ru.netology;

import com.codeborne.selenide.Condition;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class AppCardDeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void AppCardDeliveryTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Пе");
        $$(".menu-item__control").findBy(text("Петрозаводск")).click();
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов Петров Иван");
        $("[data-test-id='phone'] input").setValue("+79009009090");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")

        .shouldBe(Condition.visible, Duration.ofSeconds(15))
        .shouldBe(Condition.exactText("Встреча успешно забронирована на " + currentDate));





    }
}
