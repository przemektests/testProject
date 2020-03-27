package features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetguruTwitterPage {
    WebDriver driver;

    public NetguruTwitterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Follow']")
    WebElement followButton;

    @FindBy(xpath = "//*[@id=\"react-root\"]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[1]")
    WebElement followText;

    @FindBy(css = "nav[aria-label='Profile timelines']")
    WebElement categories;

    public void clickFollowButton(){
        followButton.click();
    }

    public String getFollowNetguruText(){
        return followText.getText();
    }

    public String[] getCategoriesList(){
        return categories.getText().split("\\r?\\n");
    }
}
