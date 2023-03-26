import com.codeborne.selenide.Configuration;
import data.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.*;

public class SelenideLocaleTest {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")),
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "При выборе {0} языка, пункты основного меню отображаются следующим образом {1}")
    public void checkThatMainMenuItemsTranslatedIntoChosenLocal(Locale locale, List<String> expectedMenuPoints) {

        open("https://www.selenide.org/");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(expectedMenuPoints));
    }
}


