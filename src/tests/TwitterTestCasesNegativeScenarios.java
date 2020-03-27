package tests;

import features.HomeSearchPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TwitterTestCasesNegativeScenarios {
    WebDriver driver;
    //region constants
    final String INVALID_SEARCH_TEXT = ".*&^$$^^&!)(";
    //endregion

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://twitter.com/explore");
        driver.manage().window().maximize();
    }

    @Test
    public void WhenInputInvalidSearchValue_ErrorMessageAndTryAgainButtonExist() {
        final String expectedErrorMsg = "Something went wrong.";
        HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
        homeSearchPage.enterSearchBoxTextValue(INVALID_SEARCH_TEXT);

        Assert.assertEquals(INVALID_SEARCH_TEXT, homeSearchPage.getSearchBoxTextValue());
        homeSearchPage.clickEnterOnSearchBox();
        boolean isSearchMsgDisplayed = homeSearchPage.isSearchErrorMessageDisplayed();
        boolean isTryAgainBtnDisplayed = homeSearchPage.isTryAgainButtonDisplayed();
        String errorMsg = homeSearchPage.getSearchErrorMessage();

        Assert.assertTrue(isSearchMsgDisplayed);
        Assert.assertTrue(isTryAgainBtnDisplayed);
        Assert.assertEquals(errorMsg, expectedErrorMsg);
    }

    @Test
    public void WhenInputInvalidSearchValue_ErrorPopUpWithMessageExists(){
        final String expectedPopUpMsg = "Something went wrong, but don’t fret — it’s not your fault.";
        HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
        homeSearchPage.enterSearchBoxTextValue(INVALID_SEARCH_TEXT);

        Assert.assertEquals(INVALID_SEARCH_TEXT, homeSearchPage.getSearchBoxTextValue());
        homeSearchPage.clickEnterOnSearchBox();
        boolean isErrorPopUpMsgDisplayed = homeSearchPage.isErrorPopUpMessageDisplayed();
        String errorPopUpMsg = homeSearchPage.getErrorPopUpMessage();

        Assert.assertTrue(isErrorPopUpMsgDisplayed);
        Assert.assertEquals(expectedPopUpMsg, errorPopUpMsg);
    }

    @After
    public void cleanUp(){
        driver.close();;
    }
}
