/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.jacekkowalczyk82.c2p;

import org.jacekkowalczyk82.c2p.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HowToPresentationWithModel {


    public static void main(String[] args) {


        Presentation pres = new Presentation();
        Configuration config = new Configuration();

        //template must be with layout empty only some background or colors no placeholders
        //use master view of slides in libre office impress
        //but footer date time and page numbers scan be
        config.setFromTemplate("Template-Libre-Clean-Yellow-Gradient.pptx");
        config.setTargetPresentationFile("HowTo-presentation-with-Model.pptx");
        config.setTitleRectangle(new PlaceHolderRectangle(50, 50, 700, 100));
        config.setSubTitleRectangle(new PlaceHolderRectangle(250, 300, 500, 100));

        config.setSlideTitleRectangle(new PlaceHolderRectangle(50, 30, 700, 80));
        config.setSlideContentRectangle(new PlaceHolderRectangle(50, 100, 680, 300));
        config.setSlideFooterRectangle(new PlaceHolderRectangle(450, 400, 300, 40));
        config.setFooterFontInfo(StyleInfo.withFontSize(14.0d));
        config.setFooterTemplate("%s         JacekKowalczyk82.org               %d");

        pres.setConfiguration(config);

        TitleSlide ts = new TitleSlide();
        ts.setTitle("Generate PPTX presentation from code");
        ts.setSubTitle("Jacek Kowalczyk 2024-04-25");

        pres.setTitleSlide(ts);

        List<Slide> mySlides = new ArrayList<>();

        pres.setSlides(mySlides);

        mySlides.add(new Slide("Setup ubuntu dev env", Content.withUlList(
                "curl -s \"https://get.sdkman.io\" | bash",
                "source \"/home/ubuntu/.sdkman/bin/sdkman-init.sh\"",
                "sdk install gradle",
                "sdk install java 11.0.22-tem",
                "gradle init"), StyleInfo.withFontSize(18.0d)));

        mySlides.add(new Slide("knowledge and links", Content.withUlList(
                        "https://www.baeldung.com/apache-poi-slideshow",
                        "shttps://poi.apache.org/components/slideshow/xslf-cookbook.html",
                        "https://github.com/eugenp/tutorials/blob/master/apache-poi-2/src/main/java/com/baeldung/poi/powerpoint/PowerPointHelper.java"),
                StyleInfo.withFontSize(18.0d)
        ));

        mySlides.add(new Slide("Building and running", Content.withCodeText(
                "./gradlew build \n" +
                        "# generate scripts and install package \n" +
                        "./gradlew installDist \n" +
                        "# Running the default DummyPresentation \n" +
                        "code2present/build/install/code2present/bin/code2present"), StyleInfo.withFontSize(18.0d)
        ));


        mySlides.add(Slide.emptySlide());

        mySlides.add(new Slide("Slide with CODE", Content.withCodeText("// Your First Program \n" +
                "\n" +
                "class HelloWorld { \n" +
                "    public static void main(String[] args) { \n" +
                "        System.out.println(\"Hello, World!\");  \n" +
                "    } \n" +
                "} \n"), StyleInfo.withFontSize(18.0d)));

        mySlides.add(new Slide("Slide with Text", Content.withText("this is a paragraph text on the slide 2"), StyleInfo.withFontSize(18.0d)));

        mySlides.add(new Slide("Slide with Bullet List", Content.withUlList(
                "Element 1 ",
                "Element 2",
                "Element 3"), StyleInfo.withFontSize(18.0d)
        ));

        mySlides.add(new Slide("Slide with Numbered List", Content.withLiList(
                "Element 1 ",
                "Element 2",
                "Element 3"), StyleInfo.withFontSize(18.0d)
        ));


        mySlides.add(new Slide("You can have empty slides", Content.withText("The next slide will be empty"), StyleInfo.withFontSize(18.0d)));
        mySlides.add(Slide.emptySlide());

        JSONTools.writePresentationToJsonFile(pres, "how-to-presentation.json");


        Code2Present c2p = new Code2Present();
        System.out.println("This is How To example presentation ");

        c2p.fromTemplate(pres.getConfiguration().getFromTemplate());
        c2p.toPresentation(pres.getConfiguration().getTargetPresentationFile());
        c2p.setTitleRectangle(pres.getConfiguration().getTitleRectangle().toAWTRectangle());
        c2p.setSubTitleRectangle(pres.getConfiguration().getSubTitleRectangle().toAWTRectangle());

        c2p.setSlideTitleRectangle(pres.getConfiguration().getSlideTitleRectangle().toAWTRectangle());
        c2p.setSlideContentRectangle(pres.getConfiguration().getSlideContentRectangle().toAWTRectangle());
        c2p.setSlideFooterRectangle(pres.getConfiguration().getSlideFooterRectangle().toAWTRectangle());
        c2p.setFooterFontInfo(pres.getConfiguration().getFooterFontInfo());

        c2p.title(pres.getTitleSlide().getTitle(), pres.getTitleSlide().getSubTitle());

        for (Slide s: pres.getSlides()) {
            c2p.slide(s.getSlideTitle(), s.getContent(), s.getContentStyleInfo());
        }

        try {
            c2p.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }




}
