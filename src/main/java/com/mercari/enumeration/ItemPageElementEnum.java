package com.mercari.enumeration;

import com.mercari.contracts.IWebElement;

public enum ItemPageElementEnum implements IWebElement {
        ITEM_INFO_DIV(Constants.item_info_div);

        private final String locator_value;

        ItemPageElementEnum(String locator_value) {
            this.locator_value = locator_value;
        }

        @Override
        public String getLocator_value(){
            return this.locator_value;
        }

        public static class Constants {
            public static final String item_info_div = "//div[@id='item-info']/section/div/mer-heading/div/div/h1";
        }

    }