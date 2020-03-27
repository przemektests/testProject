package features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeTrendsPage {
    final String TRENDING_XPATH = "//*[contains(text(),'Trending')]";
    WebDriver driver;

    public HomeTrendsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //region HomePage WebElements
    @FindBy(css = "div[aria-label='More']")
    WebElement moreTrendOpinionButton;

    @FindBy(css = "div[role='menu']")
    WebElement moreTrendOpinionInfo;

    @FindBy(xpath = "//span[text()='Show more']")
    WebElement showMore;

    @FindBy(xpath = "//div[text()='Thanks. Refresh this page to update these trends.']")
    WebElement infoAfterClicked;
    //endregion

    public String[] getTrendsOpinionMoreInfo(){
        clickMoreTrendOpinionInfoButton();
        waitForWebElementIsVisible(moreTrendOpinionInfo);
        return moreTrendOpinionInfo.getText().split("\\r?\\n");
    }

    public String chooseTrendOpinion(String trendOpinion){
        clickMoreTrendOpinionInfoButton();
        waitForWebElementIsVisible(moreTrendOpinionInfo);
        String xpathPattern = "//span[text()='PATH']";
        String xPathToFind = xpathPattern.replaceAll("PATH", trendOpinion);
        var currentTrendOpt = driver.findElement(By.xpath(xPathToFind));
        currentTrendOpt.click();
        waitForWebElementIsVisible(infoAfterClicked);
        String trendOptionText = infoAfterClicked.getText();
        driver.navigate().refresh();
        return trendOptionText;
    }

    public List<WebElement> getDefaultTrendsAmount(){
        return driver.findElements(By.xpath(TRENDING_XPATH));
    }

    public List<WebElement> getAllTrends() {
        waitForWebElementIsVisible(showMore);
        showMore.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) drv -> {
            assert drv != null;
            return drv.findElements(By.xpath(TRENDING_XPATH)).size() == 20; //After clicked 'Show more' there should
                                                                            // be 20 items on Home page
        });
        return driver.findElements(By.xpath(TRENDING_XPATH));
    }

    //region private methods
    private void clickMoreTrendOpinionInfoButton(){
        waitForWebElementIsVisible(showMore);
        moreTrendOpinionButton.click();
    }

    private void waitForWebElementIsVisible(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    //endregion
}
