package com.example.facstest1.model;

public enum ImageType {
    JPEG("image/jpeg"),
    PNG("image/png");
    private String value;

    ImageType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
