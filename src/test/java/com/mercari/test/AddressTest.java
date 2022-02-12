package com.mercari.test;

import com.mercari.annotations.TestMethodParameters;
import com.mercari.dataprovider.PropertiesDataProvider;
import com.mercari.enumeration.SearchPageElementEnum;
import com.mercari.enumeration.ShippingAddressPageElementEnum;
import com.mercari.pages.*;
import com.mercari.testrunner.TestInitializer;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class AddressTest extends TestInitializer {
    @TestMethodParameters(propertiesFile = "AddressTest.properties")
    @Test(description = "To check whether address is correctly added or not",dataProvider = "getPropertiesData",dataProviderClass = PropertiesDataProvider.class)
    public void addAddressTest(HashMap<String,String> testData) throws NoSuchFieldException, IllegalAccessException {
        HomePage homePage = new HomePage(driver);
        PersonalInformationPage personalInformationPage = homePage.clickMyPage();
        ShippingAddressPage shippingAddressPage = personalInformationPage.clickShippingAddress();
        shippingAddressPage.saveNewAddress(testData.get("apartmentName"),testData.get("apartmentNumber"),testData.get("street"),testData.get("district"),testData.get("state"),testData.get("country"),testData.get("pincode"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.APARTMENTNAMETEXT_DIV),testData.get("apartmentName"));
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.APARTMENTNUMBERTEXT_DIV),testData.get("apartmentNumber"));
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.STREETTEXT_DIV),testData.get("street"));
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.DISTRICTTEXT_DIV),testData.get("district"));
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.STATETEXT_DIV),testData.get("state"));
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.COUNTRYTEXT_DIV),testData.get("country"));
        softAssert.assertEquals(shippingAddressPage.getElementTExt(ShippingAddressPageElementEnum.PINCODETEXT_DIV),testData.get("pincode"));
        softAssert.assertAll();
    }
}
