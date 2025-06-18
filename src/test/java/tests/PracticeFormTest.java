package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void SetUp(){

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.holdBrowserOpen = true; - браузер не будет закрываться после выполнения тестов
        //Configuration.timeout = 5000; - корректировка таймаута
    }


    @Test
    void fillFormTest() {

        //указываем нужную ссылку
        open("/automation-practice-form");

        //Указываем фамилию, имя и почту
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("Ivanov@123.com");

        //Указываем номер тлф
        $("#userNumber").setValue("89999990000");

        //Указываем адрес
        $("#currentAddress").setValue("Some address 123");

        //Указываем пол
        $("#gender-radio-1").parent().click();

        //Указываем дату
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--024").click();

        //Указываем предметы
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();


        //Указываем хобби
        $("#hobbies-checkbox-1").parent().click();

        //Загружаем картинку
        $("#uploadPicture").uploadFromClasspath("Img/photo_2022-12-19_20-56-33.jpg");
        //$("#uploadPicture").uploadFile(new File("src/test/resources/Img/photo_2022-12-19_20-56-33.jpg")); - один из вариантов загрузки

        //Указываем штат и город
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        //Нажимаем на кнопку Submit
        $("#submit").click();

        //Проверяем всё то, что заполнили
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text("Ivan Ivanov"),
                text("Ivanov@123.com"),
                text("Male"),
                text("8999999000"),
                text("24 February,1999"),
                text("Physics, English"),
                text("Sports"),
                text("photo_2022-12-19_20-56-33.jpg"),
                text("Some address 123"),
                text("NCR Delhi")
                );
    }
}
