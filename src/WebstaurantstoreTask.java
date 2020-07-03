import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebstaurantstoreTask {

	public static void main(String args[]) {

		// Setting ChromeDriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Farrukh\\eclipse-workspace\\Webstaurantstore\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// navigating to website
		driver.get("https://www.webstaurantstore.com/");

		// searching for "stainless work table"
		WebElement searchBar = driver.findElement(By.id("searchval"));
		searchBar.sendKeys("stainless work table");

		WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
		searchButton.click();

		// Checking the search result ensuring every product item has the word "Table"
		// its title.

		List<WebElement> results = driver.findElements(By.xpath("//div[@class='details']//a[@class='description']"));
		String table = "Table";
		List<WebElement> addToCart = driver.findElements(By.xpath("//input[@name='addToCartButton']"));

		for (int i = 0; i < results.size(); i++) {
			String result = results.get(i).getText();

			if (!result.contains(table)) {
				System.out.println(result);
			}

			// Adding the last of found items to Cart.
			if (i == results.size() - 1) {
				addToCart.get(addToCart.size() - 1).click();
			}
		}

		// Going to View Cart page and Emptying the cart.
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='View Cart'] ")));

		WebElement viewCart = driver.findElement(By.xpath("//a[text()='View Cart'] "));
		viewCart.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Empty Cart'] ")));
		WebElement emptyCart = driver.findElement(By.xpath("//a[text()='Empty Cart']"));
		emptyCart.click();

		driver.quit();
	}

}
