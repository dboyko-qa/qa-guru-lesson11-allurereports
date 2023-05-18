package ru.boyko.darya.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.boyko.darya.pages.GitHubPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static ru.boyko.darya.utils.Constants.*;

public class IssueNameTests {

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());

    }


    @Test
    public void testIssueNameWithSelenideLogger() {
        open(START_PAGE);
        $(".header-search-input").click();
        $(".header-search-input").setValue(REPOSITORY);
        $(".header-search-input").pressEnter();
        $($("[href='/selenide/selenide']")).click();
        $("[id='issues-tab']").click();
        $(withText(ISSUE_NAME)).should(Condition.exist);

    }

    @Test
    public void testIssueNameWithLambdaStep() {
        step("Open GitHub main page", () -> {
                    open(START_PAGE);
        });

        step("Search repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").pressEnter();
        });

        step("Open repository " + REPOSITORY, () -> {
            $(String.format("[href='%s']", REPOSITORY_HREF)).click();
        });

        step("Open Issue tab", () -> {
                    $("[id='issues-tab']").click();
        });

        step("Verify that the list of issues contain issue with text " + ISSUE_NAME, () -> {
                    $(withText(ISSUE_NAME)).should(Condition.exist);
        });

    }

    @Test
    public void testIssueNameWithStepAnnotation() {
        GitHubPage gitHubPage = new GitHubPage();
        gitHubPage.openMainPage(START_PAGE)
                .searchRepository(REPOSITORY)
                .openRepository(REPOSITORY_HREF)
                .openIssuesTab()
                .isIssueExist(ISSUE_NAME);
    }

    }
