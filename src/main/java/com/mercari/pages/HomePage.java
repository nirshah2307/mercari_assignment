package com.mercari.pages;

import com.mercari.contracts.IPageElementActivity;
import com.mercari.contracts.IWebElement;
import com.mercari.enumeration.HomePageElementEnum;
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
public class HomePage implements IPageElementActivity {

    protected final WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = HomePageElementEnum.Constants.searchbar_input)
	private WebElement searchbar_input;

	@FindBy(how = How.XPATH, using = HomePageElementEnum.Constants.searchbutton_div)
	private WebElement searchbutton_div;

	@FindBy(how = How.XPATH, using = HomePageElementEnum.Constants.mypage_button)
	private WebElement mypage_button;


	public SearchPage searchText(String searchText) {
		UserActionUtility.enterText(searchbar_input,searchText);
		UserActionUtility.clickOnWebElement(searchbutton_div);
		return new SearchPage(driver);
	}

	public PersonalInformationPage clickMyPage(){
		UserActionUtility.clickOnWebElement(mypage_button);
		return new PersonalInformationPage(driver);
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
