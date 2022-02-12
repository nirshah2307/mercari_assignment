package com.mercari.enumeration;

public enum ResourcesEnum {
    TEST("test"),
    JAVA("java");

    private final String resourceDir;
    ResourcesEnum(String resourceDir) {
        this.resourceDir = resourceDir;
    }
}
