package features;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomeSearchPage {
    final String SEARCH_ATTRIBUTE_NAME = "value";
    WebDriver driver;

    public HomeSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //region HomePage WebElements
    @FindBy(css = "input[aria-label='Search query']")
    WebElement searchBox;

    @FindBy(xpath = "//span[text()='@netguru']")
    WebElement netguruOnSearchList;

    @FindBy(xpath = "//span[text()='Something went wrong.']")
    WebElement errorMessageFromSearch;

    @FindBy(xpath = "//span[text()='Try again']")
    WebElement tryAgainButton;

    @FindBy(xpath = "//span[contains(text(),'Something went wrong, but')]")
    WebElement errorPopUpMessage;
    //endregion
    //region public methods
    public void enterSearchBoxTextValue(String inputText){
        searchBox.sendKeys(inputText);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .attributeToBe(
                        searchBox, SEARCH_ATTRIBUTE_NAME, inputText));
    }

    public String getSearchBoxTextValue(){
        return searchBox.getAttribute(SEARCH_ATTRIBUTE_NAME);
    }

    public boolean isNetguruExistsOnSearchList(){
        return netguruOnSearchList.isDisplayed();
    }

    public void chooseNetguruFromSearchList() throws InterruptedException {
        /*
        * This part of code is using Thread.sleep() method because no other known method/way/solution
        * doesn' help to achieve click() action of 'Netguru' result from search list.
        * I'm aware of that is the 'not sophisticated' solution but honestly after verifying many solutions
        * like below, I faced with situation where only sleep() method works properly. I tried the following steps:
        *   - explicit wait with declared condition (e.g. wait for clickable object, wait for visible object,
        *       wait for all elements on search lists are presence),
        *   - double-click,
        *   - change type of selector and value to another object (after entered 'Netguru" in search there are more than one way to click
        *       Netguru twitter page)
        *
        * Of course I'm also opened for more approaches from more experienced people cause I think somewhere, somehow,
        * somebody know knows how to implement this in right way as high-quality tests expected.
        * */
        Thread.sleep(500);
        netguruOnSearchList.click();
    }

    public void clickEnterOnSearchBox(){
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean isSearchErrorMessageDisplayed(){
        return errorMessageFromSearch.isDisplayed();
    }

    public String getSearchErrorMessage(){
        return errorMessageFromSearch.getText();
    }

    public boolean isTryAgainButtonDisplayed(){
        return tryAgainButton.isDisplayed();
    }

    public boolean isErrorPopUpMessageDisplayed(){
        tryAgainButton.click();
        waitForWebElementIsVisible(errorPopUpMessage);
        return errorPopUpMessage.isDisplayed();
    }

    public String getErrorPopUpMessage(){
        tryAgainButton.click();
        waitForWebElementIsVisible(errorPopUpMessage);
        return errorPopUpMessage.getText();
    }
    //endregion
    //region private methods
    private void waitForWebElementIsVisible(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    //endregion
}
