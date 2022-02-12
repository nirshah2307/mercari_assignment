package com.mercari.test;

import com.mercari.annotations.TestMethodParameters;
import com.mercari.dataprovider.PropertiesDataProvider;
import com.mercari.enumeration.SearchPageElementEnum;
import com.mercari.pages.HomePage;
import com.mercari.pages.ItemPage;
import com.mercari.pages.SearchPage;
import com.mercari.testrunner.TestInitializer;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class ItemSearchTest extends TestInitializer {
    @TestMethodParameters(propertiesFile = "ProductSearchTest.properties")
    @Test(description = "To check whether search item is correct or not",dataProvider = "getPropertiesData",dataProviderClass = PropertiesDataProvider.class)
    public void searchItemTest(HashMap<String,String> testData) throws NoSuchFieldException, IllegalAccessException {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = homePage.searchText(testData.get("product"));
        Assert.assertTrue(searchPage.isElementDisplayed(SearchPageElementEnum.ITEMNUMBER_UL));
        ItemPage itemPage = searchPage.clickOnItem(SearchPageElementEnum.ITEMNUMBER_UL);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(itemPage.getItemInfo().contains("MacBook"));
        softAssert.assertTrue(itemPage.getTitle().contains("MacBook"));
        softAssert.assertAll();
    }
}
