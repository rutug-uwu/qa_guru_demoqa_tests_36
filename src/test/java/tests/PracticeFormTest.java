package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void setUp(){

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

        //убираем ненужную рекламу
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

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

        $(".table-responsive")
                .$(byText("Student Name"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("Ivan Ivanov"));

        $(".table-responsive")
                .$(byText("Student Email"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("Ivanov@123.com"));

        $(".table-responsive")
                .$(byText("Gender"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("Male"));

        $(".table-responsive")
                .$(byText("Mobile"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("8999999000"));

        $(".table-responsive")
                .$(byText("Date of Birth"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("24 February,1999"));

        $(".table-responsive")
                .$(byText("Subjects"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("Physics, English"));

        $(".table-responsive")
                .$(byText("Hobbies"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("Sports"));

        $(".table-responsive")
                .$(byText("Picture"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("photo_2022-12-19_20-56-33.jpg"));

        $(".table-responsive")
                .$(byText("Address"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("Some address 123"));

        $(".table-responsive")
                .$(byText("State and City"))
                .parent()
                .$("td", 1)
                .shouldHave(exactText("NCR Delhi"));
    }
}