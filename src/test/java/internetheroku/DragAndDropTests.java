package internetheroku;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

// =========================================================
//TESTS FOR https://the-internet.herokuapp.com/drag_and_drop
// =========================================================

public class DragAndDropTests {

    @BeforeAll
    static void setup() {
        //pay attention to the trailing slash, because it concatenates with open() and may result in 2 slashes
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.browser = "Chrome";
        Configuration.browserSize = "1920x1200";
        Configuration.timeout = 5000; //5 sec; default is 4 sec

    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void dragAndDropUsingSelenideActionsTest() {
        open("/drag_and_drop");

        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        actions()
                .moveToElement($("#column-a"))
                .clickAndHold()
                .moveToElement($("#column-b"))
                .release()
                .perform();

        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void dragAndDropCommandTest() {
        open("/drag_and_drop");

        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        $("#column-a").dragAndDrop(to("#column-b"));

        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}