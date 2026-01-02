package examples;

import static examples.ActionsBase.longPress;
import static examples.ActionsBase.doSwipe;
import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class NativeTodoProject {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setUdid("emulator-5556");
        options.setAppPackage("com.app.todolist");
        options.setAppActivity(".view.MainActivity");
        options.noReset();

        URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void createTasks() {

        driver.findElement(AppiumBy.id("com.app.todolist:id/fab_new_task")).click();
        driver.findElement(AppiumBy.id("et_new_task_name")).sendKeys("Complete Activity 1");
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click();

        driver.findElement(AppiumBy.id("fab_new_task")).click();
        driver.findElement(AppiumBy.id("et_new_task_name")).sendKeys("Complete Activity 2");
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click();

        driver.findElement(AppiumBy.id("fab_new_task")).click();
        driver.findElement(AppiumBy.id("et_new_task_name")).sendKeys("Complete Activity 3");
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click();

        List<WebElement> tasks =
                driver.findElements(AppiumBy.id("com.app.todolist:id/tv_task_name"));

        assertEquals(tasks.size(), 3);
    }

    @Test(priority = 2)
    public void editThirdTaskAndProgress() {

        Dimension dims = driver.manage().window().getSize();

        Point longPressPoint =
                new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.28));

        Point sliderStart =
                new Point((int)(dims.getWidth() * 0.27), (int)(dims.getHeight() * 0.56));

        Point sliderEnd =
                new Point((int)(dims.getWidth() * 0.59), (int)(dims.getHeight() * 0.56));

        List<WebElement> checkBoxes =
                driver.findElements(AppiumBy.id("com.app.todolist:id/cb_task_done"));

        checkBoxes.get(0).click();
        checkBoxes.get(1).click();

        longPress(driver, longPressPoint);

        driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='Edit To-Do Task']")
        ).click();

        doSwipe(driver, sliderStart, sliderEnd, 2000);

        driver.findElement(AppiumBy.id("bt_new_task_ok")).click();

        String progress =
                driver.findElements(AppiumBy.className("android.widget.ProgressBar"))
                        .get(2)
                        .getText();

        assertEquals(progress, "50.0");
    }

    @Test(priority = 3)
    public void completedTasksAssertion() {

        driver.findElement(AppiumBy.accessibilityId("More options")).click();
        driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='Completed tasks']")
        ).click();

        List<WebElement> completedTasks =
                driver.findElements(AppiumBy.id("com.app.todolist:id/rl_exlv_task_group_root"));

        assertEquals(completedTasks.size(), 2);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
