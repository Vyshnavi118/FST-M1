package examples;

import static examples.ActionsBase.doSwipe;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ChromeWebProject {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ==============================
    // 1️⃣ To-Do List Web Test
    // ==============================
    @Test(priority = 1)
    public void chromeTodoListTest() {

        driver.get("https://training-support.net/webelements");

        Dimension dims = driver.manage().window().getSize();
        Point start = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.85));
        Point end   = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.5));

        doSwipe(driver, start, end, 100);

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'To-Do List')]")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.EditText[@resource-id='todo-input']")
        ));

        WebElement input =
                driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='todo-input']"));
        WebElement addBtn =
                driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='todo-add']"));

        input.sendKeys("Add tasks to list");
        addBtn.click();

        input.sendKeys("Get number of tasks");
        addBtn.click();

        input.sendKeys("Clear the list");
        addBtn.click();

        List<WebElement> tasks =
                driver.findElements(AppiumBy.xpath("//android.widget.ListView/android.view.View"));

        // 2 default + 3 added
        assertEquals(tasks.size(), 5);
    }

    // ==============================
    // 2️⃣ Login Form – Valid & Invalid
    // ==============================
    @Test(priority = 2)
    public void chromeLoginFormTest() {

        driver.get("https://training-support.net/webelements");

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Login Form')]")
        )).click();

        // VALID LOGIN
        driver.findElement(AppiumBy.id("username")).sendKeys("admin");
        driver.findElement(AppiumBy.id("password")).sendKeys("password");
        driver.findElement(AppiumBy.id("submit")).click();

        String successMsg =
                driver.findElement(AppiumBy.id("action-confirmation")).getText();
        assertTrue(successMsg.contains("Welcome Back"));

        // INVALID LOGIN
        driver.findElement(AppiumBy.id("username")).clear();
        driver.findElement(AppiumBy.id("password")).clear();

        driver.findElement(AppiumBy.id("username")).sendKeys("admin");
        driver.findElement(AppiumBy.id("password")).sendKeys("wrong");
        driver.findElement(AppiumBy.id("submit")).click();

        String errorMsg =
                driver.findElement(AppiumBy.id("action-confirmation")).getText();
        assertTrue(errorMsg.contains("Invalid"));
    }

    // ==============================
    // 3️⃣ Popups Login Test
    // ==============================
    @Test(priority = 3)
    public void chromePopupLoginTest() {

        driver.get("https://training-support.net/webelements");

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Popups')]")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("launcher")
        )).click();

        driver.findElement(AppiumBy.id("username")).sendKeys("admin");
        driver.findElement(AppiumBy.id("password")).sendKeys("password");
        driver.findElement(AppiumBy.id("submit")).click();

        String popupMsg =
                driver.findElement(AppiumBy.id("action-confirmation")).getText();
        assertTrue(popupMsg.contains("Welcome Back"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
