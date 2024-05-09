package org.jacekkowalczyk82.c2p.model;

import java.awt.*;

public class PlaceHolderRectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    public PlaceHolderRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public PlaceHolderRectangle() {

    }

    public int getX() {
        return x;
    }

    public Rectangle toAWTRectangle() {
        return new Rectangle(x, y, width, height) ;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
