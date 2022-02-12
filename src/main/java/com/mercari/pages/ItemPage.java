package com.mercari.pages;

import com.mercari.contracts.IPageElementActivity;
import com.mercari.contracts.IWebElement;
import com.mercari.enumeration.ItemPageElementEnum;
import com.mercari.enumeration.SearchPageElementEnum;
import com.mercari.utils.UserActionUtility;
import org.graalvm.compiler.lir.LIRInstruction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;

/**
 *
 * @author Nirmal.Shah
 */
public class ItemPage implements IPageElementActivity {

    protected final WebDriver driver;

	public ItemPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = ItemPageElementEnum.Constants.item_info_div)
	private WebElement item_info_div;

	public String getItemInfo(){
		return UserActionUtility.getWebElementText(item_info_div);
	};

	@Override
	public boolean isElementDisplayed(IWebElement webElementEnum) throws NoSuchFieldException, IllegalAccessException {
		Class<?> c = this.getClass();
		Field webElement = c.getDeclaredField(webElementEnum.toString());
		if(webElement.getType()!=WebElement.class)
			throw new NoSuchFieldException();
		else
			return UserActionUtility.isElementDisplayed((WebElement) webElement.get(this));
	}

	@Override
	public boolean isElementEnabled(IWebElement webElementEnum) throws NoSuchFieldException, IllegalAccessException {
		Class<?> c = this.getClass();
		Field webElement = c.getDeclaredField(webElementEnum.toString());
		if(webElement.getType()!=WebElement.class)
			throw new NoSuchFieldException();
		else
			return UserActionUtility.isElementEnabled((WebElement) webElement.get(this));
	}

	@Override
	public void clickOnElement(IWebElement webElementEnum) throws NoSuchFieldException, IllegalAccessException {
		Class<?> c = this.getClass();
		Field webElement = c.getDeclaredField(webElementEnum.toString());
		if(webElement.getType()!=WebElement.class)
			throw new NoSuchFieldException();
		else
			UserActionUtility.clickOnWebElement((WebElement) webElement.get(this));
	}

	@Override
	public String getTitle() {
		return UserActionUtility.getTitle(driver);
	}
}
