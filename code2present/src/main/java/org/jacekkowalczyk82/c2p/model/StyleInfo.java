package org.jacekkowalczyk82.c2p.model;

public class StyleInfo {
    private double fontSize;
    private String bulletCharacters;


    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public static StyleInfo withFontSize(double fontSize) {
        StyleInfo fi = new StyleInfo();
        fi.setFontSize(fontSize);
        return fi;
    }

    public String getBulletCharacters() {
        return bulletCharacters;
    }

    public void setBulletCharacters(String bulletCharacters) {
        this.bulletCharacters = bulletCharacters;
    }
}
