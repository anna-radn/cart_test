package cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.SearchPage;
import util.BrowserFactory;

public class SearchAndTestCartPageTest {

	WebDriver driver;
	
	
	@Test
	public void userShouldBeAbleToSearch() {
		
	driver= BrowserFactory.init();
	
	SearchPage searchPage=PageFactory.initElements(driver, SearchPage.class);
	searchPage.enterTextIntoSearchBar("stainless work table");
	searchPage.clickOnSearchButton();
	searchPage.verifySearchResult("Table");
	searchPage.addLastFoundItemToCart();
	
	CartPage cartPage=PageFactory.initElements(driver, CartPage.class);
	cartPage.emptyCart();
	
	BrowserFactory.tearDown();
	}
	
	
	
}
