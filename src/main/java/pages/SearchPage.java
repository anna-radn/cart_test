package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchPage extends BasePage {

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='searchval']")
	WebElement SEARCH_BAR_ELEMENT;
	@FindBy(how = How.XPATH, using = "//button[@value='Search']")
	WebElement SEARCH_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//h1[@class='page-header search--title']")
	WebElement SEARCH_RESULT_ELEMENT;
	@FindBy(how = How.XPATH, using = "//li[@class='inline-block leading-4 align-top rounded-r-md']")
	WebElement NEXT_PAGE_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "(//input[@class='btn btn-cart btn-small'])[56]")
	WebElement ADD_TO_CART_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//button[@type='submit'][contains(text(), 'Add To Cart')]")
	WebElement ADD_TO_CART_CONFIRM_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//div/child::a[@class='btn btn-small btn-primary']")
	WebElement VIEW_CART_BUTTON_ELEMENT;

	public void enterTextIntoSearchBar(String text) {
		SEARCH_BAR_ELEMENT.sendKeys(text);
	}

	public void clickOnSearchButton() {
		SEARCH_BUTTON_ELEMENT.click();
	}

	public void verifySearchResult(String searchedItem) {

		String result = SEARCH_RESULT_ELEMENT.getText();
		System.out.println(result);

		List<WebElement> productList = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));

		List<String> names = new ArrayList<String>();
		for (WebElement productNames : productList) {
			if (productNames.getText().contains(searchedItem)) {
				names.add(productNames.getText());
			}
		}
		for (int i = 1; i < 9; i++) {
			
			NEXT_PAGE_BUTTON_ELEMENT.click();
			
			productList = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
			
			if(i==8) {
			for (int retry = 0; retry < 3; i++) {
				try {
					driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
				break;
				} catch (StaleElementReferenceException e) {
					System.out.println(e.toString());
				}
			}
				for (WebElement productNames : productList) {
					if (productNames.getText().contains(searchedItem)) {
						names.add(productNames.getText());
					}
				}
					NEXT_PAGE_BUTTON_ELEMENT.click();
				}
			}

			for (String name : names) {
				System.out.println(name);
			}
			
		int numberOfProducts = names.size();
		System.out.println("Total number of products containing Table is: " + numberOfProducts);

	}

	public void addLastFoundItemToCart() {
		driver.findElement(By.xpath("(//*[@id='ProductBoxContainer'])[60]"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ADD_TO_CART_BUTTON_ELEMENT.click();
		ADD_TO_CART_CONFIRM_BUTTON_ELEMENT.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VIEW_CART_BUTTON_ELEMENT.click();

	}

}
