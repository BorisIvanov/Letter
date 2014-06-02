package com.letter.helpers;


public enum ScanContentType {

    JPG("image/jpeg", "jpg"),
    PDF("application/pdf", "pdf");

    private final String value;
    private final String ext;

    public static String getExt(String contentValue) {
        for (ScanContentType scanContentType : ScanContentType.values()) {
            if (scanContentType.getValue().equalsIgnoreCase(contentValue))
                return scanContentType.getExt();
        }
        return null;
    }

    private ScanContentType(String value, String ext) {
        this.value = value;
        this.ext = ext;
    }

    public String getValue() {
        return value;
    }

    public String getExt() {
        return ext;
    }
}
