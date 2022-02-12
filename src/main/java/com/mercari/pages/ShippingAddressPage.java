package com.mercari.pages;

import com.mercari.contracts.IPageElementActivity;
import com.mercari.contracts.IWebElement;
import com.mercari.enumeration.HomePageElementEnum;
import com.mercari.enumeration.ShippingAddressPageElementEnum;
import com.mercari.utils.UserActionUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;

/**
 * @author Nirmal.Shah
 */
public class ShippingAddressPage implements IPageElementActivity {

    protected final WebDriver driver;

	public ShippingAddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.addshipping_div)
	private WebElement addshipping_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.apartmentName_input)
	private WebElement apartmentName_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.apartmentNumber_input)
	private WebElement apartmentNumber_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.street_input)
	private WebElement street_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.state_input)
	private WebElement state_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.district_input)
	private WebElement district_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.country_input)
	private WebElement country_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.pincode_input)
	private WebElement pincode_input;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.save_button)
	private WebElement save_button;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.apartmentNameText_div)
	private WebElement apartmentNameText_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.apartmentNumberText_div)
	private WebElement apartmentNumberText_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.streetText_div)
	private WebElement streetText_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.stateText_div)
	private WebElement stateText_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.districtText_div)
	private WebElement districtText_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.countryText_div)
	private WebElement countryText_div;

	@FindBy(how = How.XPATH, using = ShippingAddressPageElementEnum.Constants.pincodeText_div)
	private WebElement pincodeText_div;

	public ShippingAddressPage saveNewAddress(String apartmentName,String apartmentNumber, String street, String state, String district, String country,String pincode) {
		UserActionUtility.enterText(apartmentName_input,apartmentName);
		UserActionUtility.enterText(apartmentNumber_input,apartmentNumber);
		UserActionUtility.enterText(street_input,street);
		UserActionUtility.enterText(state_input,state);
		UserActionUtility.enterText(district_input,district);
		UserActionUtility.enterText(country_input,country);
		UserActionUtility.enterText(pincode_input,pincode);
		UserActionUtility.clickOnWebElement(save_button);
		return this;
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

	public String getElementTExt(IWebElement webElementEnum) throws NoSuchFieldException, IllegalAccessException {
		Class<?> c = this.getClass();
		Field webElement = c.getDeclaredField(webElementEnum.toString());
		if(webElement.getType()!=WebElement.class)
			throw new NoSuchFieldException();
		else
			return UserActionUtility.getWebElementText((WebElement) webElement.get(this));
	}

	@Override
	public String getTitle() {
		return UserActionUtility.getTitle(driver);
	}
}
