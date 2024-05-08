package org.jacekkowalczyk82.c2p.model;

import java.awt.*;

public class Configuration {
    private String fromTemplate;
    private String targetPresentationFile;
    private String footerTemplate;

    private PlaceHolderRectangle titleRectangle;
    private PlaceHolderRectangle subTitleRectangle;

    private PlaceHolderRectangle slideTitleRectangle;
    private PlaceHolderRectangle slideContentRectangle;
    private PlaceHolderRectangle slideFooterRectangle;
    private FontInfo footerFontInfo;

    public String getFromTemplate() {
        return fromTemplate;
    }

    public void setFromTemplate(String fromTemplate) {
        this.fromTemplate = fromTemplate;
    }

    public String getTargetPresentationFile() {
        return targetPresentationFile;
    }

    public void setTargetPresentationFile(String targetPresentationFile) {
        this.targetPresentationFile = targetPresentationFile;
    }

    public String getFooterTemplate() {
        return footerTemplate;
    }

    public void setFooterTemplate(String footerTemplate) {
        this.footerTemplate = footerTemplate;
    }

    public PlaceHolderRectangle getTitleRectangle() {
        return titleRectangle;
    }

    public void setTitleRectangle(PlaceHolderRectangle titleRectangle) {
        this.titleRectangle = titleRectangle;
    }

    public PlaceHolderRectangle getSubTitleRectangle() {
        return subTitleRectangle;
    }

    public void setSubTitleRectangle(PlaceHolderRectangle subTitleRectangle) {
        this.subTitleRectangle = subTitleRectangle;
    }

    public PlaceHolderRectangle getSlideTitleRectangle() {
        return slideTitleRectangle;
    }

    public void setSlideTitleRectangle(PlaceHolderRectangle slideTitleRectangle) {
        this.slideTitleRectangle = slideTitleRectangle;
    }

    public PlaceHolderRectangle getSlideContentRectangle() {
        return slideContentRectangle;
    }

    public void setSlideContentRectangle(PlaceHolderRectangle slideContentRectangle) {
        this.slideContentRectangle = slideContentRectangle;
    }

    public PlaceHolderRectangle getSlideFooterRectangle() {
        return slideFooterRectangle;
    }

    public void setSlideFooterRectangle(PlaceHolderRectangle slideFooterRectangle) {
        this.slideFooterRectangle = slideFooterRectangle;
    }

    public FontInfo getFooterFontInfo() {
        return footerFontInfo;
    }

    public void setFooterFontInfo(FontInfo footerFontInfo) {
        this.footerFontInfo = footerFontInfo;
    }
}
