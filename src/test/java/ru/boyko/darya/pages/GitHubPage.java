package ru.boyko.darya.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubPage {
    @Step("Open github main page")
    public GitHubPage openMainPage(String site){
        open(site);
        return this;
    }

    @Step("Search for repository {repo}")
    public GitHubPage searchRepository(String repo){
        $(".header-search-input").click();
        $(".header-search-input").setValue(repo);
        $(".header-search-input").pressEnter();
        return this;
    }

    @Step("Open repository {repoHref} in the list of search results")
    public GitHubPage openRepository(String repoHref){
        $(String.format("[href='%s']", repoHref)).click();
        return this;
    }

    @Step("Open Issues tab")
    public GitHubPage openIssuesTab(){
        $("[id='issues-tab']").click();
        return this;
    }

    @Step("Verify that issue {issueName} exists")
    public GitHubPage isIssueExist(String issueName){
        $(withText(issueName)).should(Condition.exist);
        return this;
    }

}
