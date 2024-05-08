package org.jacekkowalczyk82.c2p.model;

import java.util.List;

public class Presentation {
    private Configuration configuration;
    private TitleSlide titleSlide;
    private List<Slide> slides;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    public TitleSlide getTitleSlide() {
        return titleSlide;
    }

    public void setTitleSlide(TitleSlide titleSlide) {
        this.titleSlide = titleSlide;
    }
}
