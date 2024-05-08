package org.jacekkowalczyk82.c2p.model;

public class Slide {
    private String slideTitle;
    private Content content;
    private FontInfo contentFontInfo;

    public Slide(String slideTitle, Content content, FontInfo fontInfo) {
        this.setSlideTitle(slideTitle);
        this.setContent(content);
        this.setContentFontInfo(fontInfo);
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

    public FontInfo getContentFontInfo() {
        return contentFontInfo;
    }

    public void setContentFontInfo(FontInfo contentFontInfo) {
        this.contentFontInfo = contentFontInfo;
    }
}
