package org.jacekkowalczyk82.c2p.model;

public class FontInfo {
    private double fontSize;

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public static FontInfo withFontSize(double fontSize) {
        FontInfo fi = new FontInfo();
        fi.setFontSize(fontSize);
        return fi;
    }
}
