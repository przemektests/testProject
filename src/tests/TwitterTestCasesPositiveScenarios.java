package tests;

import features.HomeSearchPage;
import features.HomeTrendsPage;
import features.NetguruTwitterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TwitterTestCasesPositiveScenarios {
    WebDriver driver;
    //region constants
    final String NETGURU_NAME = "Netguru";
    String[] TREND_OPINION_LIST = new String[]{
            "This trend is spam",
            "This trend is abusive or harmful",
            "This trend is a duplicate",
            "This trend is low quality"};
    String[] TREND_OPINION_MAX_LIST = new String[]{
            "This trend is spam",
            "This trend is abusive or harmful",
            "This trend is a duplicate",
            "This trend is low quality",
            "The associated content is not relevant"};
    //endregion

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://twitter.com/explore");
        driver.manage().window().maximize();
    }

    @Test
    public void WhenUseSearchForNetguruFollow_NetguruTwitterPageFound() throws InterruptedException {
        final String expectedFollowNetguruText = "Follow Netguru to see what they share on Twitter.";
        HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
        homeSearchPage.enterSearchBoxTextValue(NETGURU_NAME);

        Assert.assertEquals(NETGURU_NAME, homeSearchPage.getSearchBoxTextValue());
        Assert.assertTrue(homeSearchPage.isNetguruExistsOnSearchList());

        homeSearchPage.chooseNetguruFromSearchList();
        NetguruTwitterPage netguruTwitterPage = new NetguruTwitterPage(driver);
        netguruTwitterPage.clickFollowButton();

        Assert.assertEquals(expectedFollowNetguruText, netguruTwitterPage.getFollowNetguruText());
    }

    @Test
    public void WhenTwitterFollowPageOpen_AllCategoriesTabsExist() throws InterruptedException {
        final String[] tabsNames = new String[]{"Tweets", "Tweets & replies", "Media", "Likes"};
        HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
        homeSearchPage.enterSearchBoxTextValue(NETGURU_NAME);

        Assert.assertEquals(NETGURU_NAME, homeSearchPage.getSearchBoxTextValue());
        Assert.assertTrue(homeSearchPage.isNetguruExistsOnSearchList());

        homeSearchPage.chooseNetguruFromSearchList();
        NetguruTwitterPage netguruTwitterPage = new NetguruTwitterPage(driver);
        var categoriesList = netguruTwitterPage.getCategoriesList();

        Assert.assertEquals(tabsNames, categoriesList);
    }

    @Test
    public void WhenClickOnTrendOpinionList_AllTrendsOpinionIsListed() {
        HomeTrendsPage homeTrendsPage = new HomeTrendsPage(driver);
        var currentTrendsMoreInfo = homeTrendsPage.getTrendsOpinionMoreInfo();

        // Below verifying are the default 4 trend opinions for particular trend on Home Page or version with
        // 5 trend opinions which is related to type of displayed trend.
        var currentTrendOpinionList = currentTrendsMoreInfo.length == 4? TREND_OPINION_LIST : TREND_OPINION_MAX_LIST;
        Assert.assertEquals(currentTrendOpinionList, currentTrendsMoreInfo);
    }

    @Test
    public void WhenChooseEachOfTrendOpinion_UpdateTrendOpinionInfoIsDisplayed() {
        final String expectedTrendsOpinionInfo = "Thanks. Refresh this page to update these trends.";
        HomeTrendsPage homeTrendsPage = new HomeTrendsPage(driver);

        // Below flow assume only case with trends which has 4 opinions on opinions list.
        // After refresh page, displaying of current trends are random, so it's a very complex to recognize
        // for each clicked trends how many trend opinions they have. Choose basic version of test flow with
        // basic 4 trend opinions version.
        for (String trendOpinion : TREND_OPINION_LIST){
            var result = homeTrendsPage.chooseTrendOpinion(trendOpinion);
            Assert.assertEquals(expectedTrendsOpinionInfo, result);
        }
    }

    @Test
    public void WhenVerifyAmountOfCurrentTrends_CorrectAmountOfTrendsIsDisplayed() {
        // Default amount of displayed trends are 5.
        final int expectedDefaultTrendsAmount = 5;
        // After clicked 'Show more' there are maximum of displayed trends on Home Page, equal 20.
        final int expectedMaxTrendsAmount = 20;
        HomeTrendsPage homeTrendsPage = new HomeTrendsPage(driver);

        var currentDefaultTrendsAmount = homeTrendsPage.getDefaultTrendsAmount();
        Assert.assertEquals(expectedDefaultTrendsAmount, currentDefaultTrendsAmount.size());

        var currentMaxTrendsAmount = homeTrendsPage.getAllTrends();
        Assert.assertEquals(expectedMaxTrendsAmount, currentMaxTrendsAmount.size());
    }

    @After
    public void cleanUp(){
        driver.close();;
    }
}
