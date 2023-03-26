import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class JUnit5Test {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {"standard_user", "performance_glitch_user"})
    @ParameterizedTest(name = "Проверить что пользователь {0} может залогиниться")
    public void checkThatUserNameAreValid(String testData) {

        open("https://www.saucedemo.com/");
        $("#user-name").setValue(testData);
        $("#password").setValue("secret_sauce");
        $("#login-button").click();
        $(".app_logo").shouldHave(Condition.text("Swag Labs"));
    }


    @CsvFileSource(resources = "/testdata/checkThatFirstResultContainsExpectedText.csv", delimiter = '|')
    @ParameterizedTest(name = "При поиске по слову {0}, первый результат содержит текст {1}")
    public void checkThatFirstResultContainsExpectedText(String testData, String expectedResult) {

        open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue(testData).pressEnter();
        $x("//div[@data-cel-widget='search_result_1']").shouldHave(Condition.text(expectedResult));
    }
}


