package com.mercari.enumeration;

import com.mercari.contracts.IWebElement;

public enum HomePageElementEnum implements IWebElement {
        SEARCHBAR_INPUT(Constants.searchbar_input),
        SEARCHBUTTON_DIV(Constants.searchbutton_div),
        MYPAGE_BUTTON(Constants.mypage_button);

        private final String locator_value;

        HomePageElementEnum(String locator_value) {
            this.locator_value = locator_value;
        }

        @Override
        public String getLocator_value(){
            return this.locator_value;
        }

        public static class Constants {
            public static final String searchbar_input = "//mer-search-input[@input-label='検索キーワードを入力']";
            public static final String searchbutton_div = "//mer-search-input/form/div[@class='button-group']";
            public static final String mypage_button = "//mer-search-input/form/button[@id='MyPage']";
        }
    }