package examples;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class Activity4 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        // IMPORTANT: specify emulator
        options.setUdid("emulator-5556");

        // Contacts app
        options.setAppPackage("com.google.android.contacts");
        options.setAppActivity("com.android.contacts.activities.PeopleActivity");
        options.noReset();

        URL serverURL = new URI("http://127.0.0.1:4723").toURL();
        driver = new AndroidDriver(serverURL, options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void contactsTest() {

        // Click Create Contact
        driver.findElement(
                AppiumBy.accessibilityId("Create new contact")
        ).click();

        // Wait for First Name field
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.EditText[@text='First name']")
        ));

        // Enter contact details
        driver.findElement(
                AppiumBy.xpath("//android.widget.EditText[@text='First name']")
        ).sendKeys("Aaditya");

        driver.findElement(
                AppiumBy.xpath("//android.widget.EditText[@text='Last name']")
        ).sendKeys("Varma");

        driver.findElement(
                AppiumBy.xpath("//android.widget.EditText[@text='Phone']")
        ).sendKeys("999148292");

        // Save contact
        driver.findElement(AppiumBy.id("editor_menu_save_button")).click();

        // Verify saved contact
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("large_title")
        ));

        String contactName =
                driver.findElement(AppiumBy.id("large_title")).getText();

        Assert.assertEquals(contactName, "Aaditya Varma");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
