package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CartPage extends BasePage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//*[@class='emptyCartButton btn btn-mini btn-ui pull-right']")
	WebElement EMPTY_CART_BUTTON_ELEMENT;

	public void emptyCart() {
		EMPTY_CART_BUTTON_ELEMENT.click();
	}

}
