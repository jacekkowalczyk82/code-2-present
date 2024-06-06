package org.jacekkowalczyk82.c2p.model;

public class Slide {
    private String slideTitle;
    private Content content;
    private StyleInfo contentStyleInfo;

    public Slide(String slideTitle, Content content, StyleInfo fontInfo) {
        this.setSlideTitle(slideTitle);
        this.setContent(content);
        this.setContentStyleInfo(fontInfo);
    }

    private Slide(){

    }

    public static Slide emptySlide(){
        return new Slide();
    }

    public String getSlideTitle() {
        return slideTitle;
    }

    public void setSlideTitle(String slideTitle) {
        this.slideTitle = slideTitle;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public StyleInfo getContentStyleInfo() {
        return contentStyleInfo;
    }

    public void setContentStyleInfo(StyleInfo contentStyleInfo) {
        this.contentStyleInfo = contentStyleInfo;
    }
}
