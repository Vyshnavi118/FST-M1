package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TSHomepageSteps extends BaseClass {

    @Given("user is on the TS homepage")
    public void openTSHomepage() {
        driver.get("https://training-support.net");
        assertEquals("Training Support", driver.getTitle());
    }

    @When("the user clicks on the About Us link")
    public void clickAboutUs() {
        driver.findElement(By.linkText("About Us")).click();
    }

    @Then("they are redirected to another page")
    public void verifyAboutUsPage() {
        wait.until(ExpectedConditions.titleIs("About Training Support"));
        String heading = driver.findElement(By.cssSelector("h1.text-center")).getText();
        assertEquals("About Us", heading);
        driver.quit();
    }
}
