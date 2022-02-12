package com.mercari.enumeration;

import com.mercari.contracts.IWebElement;

public enum ShippingAddressPageElementEnum implements IWebElement {
        APARTMENTNAME_INPUT(Constants.apartmentName_input),
        APARTMENTNUMBER_INPUT(Constants.apartmentNumber_input),
        STREET_INPUT(Constants.street_input),
        DISTRICT_INPUT(Constants.district_input),
        STATE_INPUT(Constants.state_input),
        COUNTRY_INPUT(Constants.country_input),
        PINCODE_INPUT(Constants.pincode_input),
        APARTMENTNAMETEXT_DIV(Constants.apartmentNameText_div),
        APARTMENTNUMBERTEXT_DIV(Constants.apartmentNumberText_div),
        STREETTEXT_DIV(Constants.streetText_div),
        DISTRICTTEXT_DIV(Constants.districtText_div),
        STATETEXT_DIV(Constants.stateText_div),
        COUNTRYTEXT_DIV(Constants.countryText_div),
        PINCODETEXT_DIV(Constants.pincodeText_div),
        SAVE_BUTTON(Constants.save_button),
        ADDSHIPPING_DIV(Constants.addshipping_div);

        private final String locator_value;

        ShippingAddressPageElementEnum(String locator_value) {
            this.locator_value = locator_value;
        }

        @Override
        public String getLocator_value(){
            return this.locator_value;
        }

        public static class Constants {
            public static final String apartmentNumber_input = "//input[@id='apartmentNumber']";
            public static final String apartmentName_input = "//input[@id='apartmentName']";
            public static final String street_input = "//input[@id='street']";
            public static final String district_input = "//input[@id='district']";
            public static final String state_input = "//input[@id='state']";
            public static final String country_input = "//input[@id='country']";
            public static final String pincode_input = "//input[@id='pincode']";
            public static final String addshipping_div = "//div[@id='addShipping']";
            public static final String save_button = "//button[@id='save']";
            public static final String apartmentNumberText_div = "//ul/li[1]/div[@class='apartmentNumber']";
            public static final String apartmentNameText_div = "//ul/li[1]/div[@class='apartmentName']";
            public static final String streetText_div = "//ul/li[1]/div[@class='street']";
            public static final String districtText_div = "//ul/li[1]/div[@class='district']";
            public static final String stateText_div = "//ul/li[1]/div[@class='state']";
            public static final String countryText_div = "//ul/li[1]/div[@class='country']";
            public static final String pincodeText_div = "//ul/li[1]/div[@class='pincode']";
        }

    }