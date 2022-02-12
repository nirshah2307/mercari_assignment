package com.mercari.enumeration;

import com.mercari.contracts.IWebElement;

public enum PersonalInformationPageElementEnum implements IWebElement {
        SHIPPINGADDRESS_DIV(Constants.shippingaddress_div);

        private final String locator_value;

        PersonalInformationPageElementEnum(String locator_value) {
            this.locator_value = locator_value;
        }

        @Override
        public String getLocator_value(){
            return this.locator_value;
        }

        public static class Constants {
            public static final String shippingaddress_div = "//div[@id='shippingadd']";
        }
    }