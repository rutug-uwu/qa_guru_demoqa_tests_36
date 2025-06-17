package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void SetUp(){

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.holdBrowserOpen = true; - браузер не будет закрываться после выполнения
        //Configuration.timeout = 5000; - корректировка таймаута
    }


    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Qaguru");
        $("#userEmail").setValue("Qaguru@test.com");
        $("#currentAddress").setValue("some street 123");
        $("#permanentAddress").setValue("another street 123");
        $("#submit").click();

        $("#output #name").shouldHave(text("Qaguru"));
        $("#output #email").shouldHave(text("Qaguru@test.com"));
        $("#output #currentAddress").shouldHave(text("some street 123"));
        $("#output #permanentAddress").shouldHave(text("another street 123"));
    }
}
    