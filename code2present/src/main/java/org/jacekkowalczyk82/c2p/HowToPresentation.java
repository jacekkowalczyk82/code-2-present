/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.jacekkowalczyk82.c2p;

import org.jacekkowalczyk82.c2p.model.Content;
import org.jacekkowalczyk82.c2p.model.StyleInfo;

import java.awt.*;
import java.io.IOException;


public class HowToPresentation {


    public static void main(String[] args) {
        Code2Present c2p = new Code2Present();
        System.out.println("This is How To example presentation ");

        //code-2-present-template1.pptx
        // c2p.fromTemplate("TemplateMeshWithFooter.pptx");
        // c2p.fromTemplate("TemplateMesh.pptx");
        // c2p.fromTemplate("TemplateCelestial.pptx");

        //template must be with layout empty only some background or colors no placeholders
        //use master view of slides in libre office impress
        //but footer date time and page numbers scan be
//        c2p.fromTemplate("Template-Libre-Clean-Pencil.pptx");
//        c2p.fromTemplate("Template-Libre-Clean.pptx");
        c2p.fromTemplate("Template-Libre-Clean-Yellow-Gradient.pptx");
        c2p.toPresentation("HowTo-presentation.pptx");
        c2p.setTitleRectangle(new Rectangle(50, 50, 700, 100));
        c2p.setSubTitleRectangle(new Rectangle(250, 300, 500, 100));

        c2p.setSlideTitleRectangle(new Rectangle(50, 30, 700, 80));
        c2p.setSlideContentRectangle(new Rectangle(50, 100, 680, 300));
        c2p.setSlideFooterRectangle(new Rectangle(450, 400, 300, 40));
        c2p.setFooterFontInfo(StyleInfo.withFontSize(14.0d));

//
//        XSLFTextShape titleShape = slide.createTextBox();
//        titleShape.setPlaceholder(Placeholder.TITLE);
//        titleShape.setAnchor(new Rectangle(50, 30, 700, 80));
//
//        XSLFTextShape contentShape = slide.createTextBox();
//        contentShape.setPlaceholder(Placeholder.CONTENT);
//        contentShape.setAnchor(new Rectangle(50, 100, 680, 300));
//
//        XSLFTextShape footerShape = slide.createTextBox();
//        footerShape.setPlaceholder(Placeholder.FOOTER);
//        footerShape.setAnchor(new Rectangle(400, 400, 300, 40));

        // c2p.fromTemplate("TemplateMeshWithFooter.pptx");5
        // c2p.fromTemplate("TemplateMeshWithFooter.pptx");


        /*
         * # code-2-present
Generate PPTX presentation from code


## Setup ubuntu dev env 

```
curl -s "https://get.sdkman.io" | bash

source "/home/ubuntu/.sdkman/bin/sdkman-init.sh"
sdk install gradle

sdk install java 11.0.22-tem

gradle init


```

## knowledge and links

* https://www.baeldung.com/apache-poi-slideshow
* https://poi.apache.org/components/slideshow/xslf-cookbook.html
* https://github.com/eugenp/tutorials/blob/master/apache-poi-2/src/main/java/com/baeldung/poi/powerpoint/PowerPointHelper.java
* 

## Building and running 

```
./gradlew build 


# generate scripts and install package
./gradlew installDist

# Running
code2present/build/install/code2present/bin/code2present


```

*
         */


        c2p.title("Generate PPTX presentation from code", "Jacek Kowalczyk 2024-04-25");


        c2p.slideWithContent("Setup ubuntu dev env", Content.withUlList(
                "curl -s \"https://get.sdkman.io\" | bash",
                "source \"/home/ubuntu/.sdkman/bin/sdkman-init.sh\"",
                "sdk install gradle",
                "sdk install java 11.0.22-tem",
                "gradle init"), StyleInfo.withFontSize(18.0d)
        );

        c2p.slideWithContent("knowledge and links", Content.withUlList(
                        "https://www.baeldung.com/apache-poi-slideshow",
                        "shttps://poi.apache.org/components/slideshow/xslf-cookbook.html",
                        "https://github.com/eugenp/tutorials/blob/master/apache-poi-2/src/main/java/com/baeldung/poi/powerpoint/PowerPointHelper.java"),
                StyleInfo.withFontSize(18.0d)
        );

        c2p.slideWithContent("Building and running", Content.withCodeText(
                "./gradlew build \n" +
                        "# generate scripts and install package \n" +
                        "./gradlew installDist \n" +
                        "# Running the default DummyPresentation \n" +
                        "code2present/build/install/code2present/bin/code2present"), StyleInfo.withFontSize(18.0d)
        );

        c2p.emptySlide();

        c2p.slideWithContent("Slide with CODE", Content.withCodeText("// Your First Program \n" +
                "\n" +
                "class HelloWorld { \n" +
                "    public static void main(String[] args) { \n" +
                "        System.out.println(\"Hello, World!\");  \n" +
                "    } \n" +
                "} \n"), StyleInfo.withFontSize(18.0d));

        c2p.slideWithContent("Slide with Text", Content.withText("this is a paragraph text on the slide 2"), StyleInfo.withFontSize(18.0d));

        c2p.slideWithContent("Slide with Bullet List", Content.withUlList(
                "Element 1 ",
                "Element 2",
                "Element 3"), StyleInfo.withFontSize(18.0d)
        );

        c2p.slideWithContent("Slide with Numbered List", Content.withLiList(
                "Element 1 ",
                "Element 2",
                "Element 3"), StyleInfo.withFontSize(18.0d)
        );


        c2p.slideWithContent("You can have empty slides", Content.withText("The next slide will be empty"), StyleInfo.withFontSize(18.0d));
        c2p.emptySlide();


//        c2p.slide("slide 3",Content.withImage("images/image1.png"), FontInfo.withFontSize(14.0d));


        try {
            c2p.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
