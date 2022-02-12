package com.mercari.enumeration;

import com.mercari.contracts.IWebElement;

public enum SearchPageElementEnum implements IWebElement {
        ITEMNUMBER_UL(Constants.itemNumber_ul);

        private final String locator_value;

        SearchPageElementEnum(String locator_value) {
            this.locator_value = locator_value;
        }

        @Override
        public String getLocator_value(){
            return this.locator_value;
        }

        public static class Constants {
            public static final String itemNumber_ul = "//ul[@id='item-grid']/li[3]";
        }

    }