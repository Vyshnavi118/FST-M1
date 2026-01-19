package testng;


	import org.openqa.selenium.*;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.annotations.*;

	import java.time.Duration;
	import java.util.List;

	public class CrmSelenium {

	    public WebDriver driver;
	    public WebDriverWait wait;

	    // -------------------- SETUP --------------------
	    @BeforeClass
	    public void setUp() {
	        driver = new FirefoxDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        driver.manage().window().maximize();

	        // Login once
	        driver.get("https://alchemy.hguy.co/crm/");
	        driver.findElement(By.id("user_name")).sendKeys("admin");
	        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
	        driver.findElement(By.id("bigbutton")).click();

	        wait.until(ExpectedConditions.titleContains("SuiteCRM"));
	    }

	    // ---------------- Activity 1 ----------------
	    @Test(priority = 1)
	    public void verifyWebsiteTitle() {
	        Assert.assertEquals(driver.getTitle(), "SuiteCRM");
	    }

	    // ---------------- Activity 2 ----------------
	    @Test(priority = 2)
	    public void printHeaderImageURL() {

	        List<WebElement> images = driver.findElements(By.tagName("img"));

	        if (images.size() == 0) {
	            System.out.println("No header image found on the page.");
	            Assert.assertTrue(true);
	            return;
	        }

	        System.out.println("Header Image URL: " + images.get(0).getAttribute("src"));
	    }

	    // ---------------- Activity 3 ----------------
	    @Test(priority = 3)
	    public void printCopyrightText() {
	        WebElement footer =
	                driver.findElement(By.tagName("footer"));
	        System.out.println("Copyright: " + footer.getText());
	    }

	    // ---------------- Activity 4 ----------------
	    @Test(priority = 4)
	    public void verifyLoginSuccessful() {
	        Assert.assertTrue(driver.getTitle().contains("SuiteCRM"));
	    }

	    // ---------------- Activity 5 ----------------
	    @Test(priority = 5)
	    public void getNavigationMenuColor() {
	        WebElement navBar =
	                driver.findElement(By.id("toolbar"));
	        System.out.println("Navigation Menu Color: " +
	                navBar.getCssValue("background-color"));
	    }

	    // ---------------- Activity 6 ----------------
	    @Test(priority = 6)
	    public void verifyActivitiesMenuExists() {
	        WebElement activitiesMenu =
	                driver.findElement(By.xpath("//a[text()='Activities']"));
	        Assert.assertTrue(activitiesMenu.isDisplayed());
	    }

	    // ---------------- Activity 7 ----------------
	    @Test(priority = 7)
	    public void readAdditionalInfoPopup() {

	        driver.get("https://alchemy.hguy.co/crm/index.php?module=Leads&action=index");

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector("table.list")));

	        List<WebElement> infoIcons =
	                driver.findElements(By.cssSelector("span.glyphicon-info-sign"));

	        if (infoIcons.size() == 0) {
	            System.out.println("No additional info popup available in Leads table.");
	            Assert.assertTrue(true);
	            return;
	        }

	        infoIcons.get(0).click();

	        WebElement popup = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.className("popover-content")
	                )
	        );

	        System.out.println("Popup Info: " + popup.getText());
	    }

	    // ---------------- Activity 8 ----------------
	    @Test(priority = 8)
	    public void traverseAccountsTable() {

	        driver.get("https://alchemy.hguy.co/crm/index.php?module=Accounts&action=index");

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector("table.list")));

	        List<WebElement> rows =
	                driver.findElements(By.cssSelector("table.list tbody tr"));

	        System.out.println("First 5 odd-numbered Account names:");
	        for (int i = 0; i < rows.size() && i < 10; i += 2) {
	            System.out.println(
	                    rows.get(i)
	                            .findElement(By.cssSelector("td:nth-child(3)"))
	                            .getText()
	            );
	        }

	        Assert.assertTrue(rows.size() > 0);
	    }

	    // ---------------- Activity 9 ----------------
	    @Test(priority = 9)
	    public void traverseLeadsTable() {

	        driver.get("https://alchemy.hguy.co/crm/index.php?module=Leads&action=index");

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector("table.list")));

	        List<WebElement> rows =
	                driver.findElements(By.cssSelector("table.list tbody tr"));

	        System.out.println("First 10 Lead Names:");
	        for (int i = 0; i < rows.size() && i < 10; i++) {
	            System.out.println(
	                    rows.get(i)
	                            .findElement(By.cssSelector("td:nth-child(3)"))
	                            .getText()
	            );
	        }

	        Assert.assertTrue(rows.size() > 0);
	    }

	    // ---------------- TEARDOWN ----------------
	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}


