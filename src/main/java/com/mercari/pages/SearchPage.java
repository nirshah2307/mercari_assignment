package com.mercari.pages;

import com.mercari.contracts.IPageElementActivity;
import com.mercari.contracts.IWebElement;
import com.mercari.enumeration.SearchPageElementEnum;
import com.mercari.utils.UserActionUtility;
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
public class SearchPage implements IPageElementActivity {

    protected final WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = SearchPageElementEnum.Constants.itemNumber_ul)
	private WebElement itemNumber_ul;

	public ItemPage clickOnItem(IWebElement webElementEnum) throws NoSuchFieldException, IllegalAccessException {
			clickOnElement(webElementEnum);
			return new ItemPage(driver);
	}

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
