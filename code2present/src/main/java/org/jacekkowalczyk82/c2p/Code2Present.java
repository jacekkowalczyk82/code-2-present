/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.jacekkowalczyk82.c2p;

import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;
import org.jacekkowalczyk82.c2p.model.Content;
import org.jacekkowalczyk82.c2p.model.FontInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Rectangle;


public class Code2Present {

    private XMLSlideShow presentation;
    private XSLFSlide titleSlide;
    private XSLFSlideMaster defaultMaster;
    private XSLFSlideLayout titleLayout;
    private XSLFSlideLayout layout;
    public int slideNumber;
    private String targetPresentationFileName;

    private Rectangle titleRectangle;
    private Rectangle subTitleRectangle;

    private Rectangle slideTitleRectangle;
    private Rectangle slideContentRectangle;
    private Rectangle slideFooterRectangle;
    private FontInfo footerFontInfo;
    private String footerTemplate;

    public String getFooterTemplate() {
        return footerTemplate;
    }

    public void setFooterTemplate(String footerTemplate) {
        this.footerTemplate = footerTemplate;
    }

    public FontInfo getFooterFontInfo() {
        return footerFontInfo;
    }

    public void setFooterFontInfo(FontInfo footerFontInfo) {
        this.footerFontInfo = footerFontInfo;
    }

    public Rectangle getSlideContentRectangle() {
        return slideContentRectangle;
    }

    public void setSlideContentRectangle(Rectangle slideContentRectangle) {
        this.slideContentRectangle = slideContentRectangle;
    }

    public Rectangle getSlideFooterRectangle() {
        return slideFooterRectangle;
    }

    public void setSlideFooterRectangle(Rectangle slideFooterRectangle) {
        this.slideFooterRectangle = slideFooterRectangle;
    }

    public Rectangle getSlideTitleRectangle() {
        return slideTitleRectangle;
    }

    public void setSlideTitleRectangle(Rectangle slideTitleRectangle) {
        this.slideTitleRectangle = slideTitleRectangle;
    }

    public Rectangle getSubTitleRectangle() {
        return subTitleRectangle;
    }

    public void setSubTitleRectangle(Rectangle subTitleRectangle) {
        this.subTitleRectangle = subTitleRectangle;
    }

    public Rectangle getTitleRectangle() {
        return titleRectangle;
    }

    public void setTitleRectangle(Rectangle titleRectangle) {
        this.titleRectangle = titleRectangle;
    }

    public void fromTemplate(String templateFileName) {
        try {
            presentation = new XMLSlideShow(
                    new FileInputStream(templateFileName));

            defaultMaster = presentation.getSlideMasters().get(0);

            System.out.println("Available slide layouts:");
            for(XSLFSlideMaster master : presentation.getSlideMasters()){
                for(XSLFSlideLayout layout : master.getSlideLayouts()){
                    System.out.println("    "+ layout.getType());
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getGreeting() {
        return "Hello World!";
    }

//     public static void main(String[] args) {
//         Code2Present c2p = new Code2Present();
//         System.out.println(c2p.getGreeting());

//         //code-2-present-template1.pptx
//         c2p.fromTemplate("TemplateMeshWithFooter.pptx");
//         // c2p.fromTemplate("TemplateMesh.pptx");
//         // c2p.fromTemplate("TemplateCelestial.pptx");
//         // c2p.fromTemplate("TemplateMeshWithFooter.pptx");
//         // c2p.fromTemplate("TemplateMeshWithFooter.pptx");


//         c2p.title("This is Title", "Subtitle Author date");
        
//         c2p.emptySlide();

//         c2p.slide("slide 2", Content.withText("this is a paragraph text on the slide 2"));
        
//        c2p.slide("slide 3", Content.withUlList(
//                "Elem 1",
//                "Elem 2", "Elemment 3 ")
//        );

// //        c2p.slide("slide 3",Content.withImage("images/image1.png"));


//         try {
//             c2p.save("example-presentation.pptx");
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }


//     }

    public void copyPlaceHoldersFromTemplate(XSLFSlide titleSlide, XSLFSlide slide) {  
  
        List<XSLFShape> titleSlideShapes = titleSlide.getShapes();  
        for (XSLFShape shape : titleSlideShapes) {  
            Placeholder ph = shape.getPlaceholder();  
  
                System.out.println("Copying from " + shape.getShapeName() + " " + shape.getPlaceholder());  
//                ((XSLFTextShape) shape).setText("Test Text");  
  
                if (ph == null) {  
                    continue;  
                }  
  
                switch (ph) {  
                // these are special and not copied by default  
                    case DATETIME:  
                    case SLIDE_NUMBER:
//                    case CONTENT:
                    case FOOTER:  
                        System.out.println("Copying placeholder : "+ ph);  
                        slide.getXmlObject().getCSld().getSpTree().addNewSp().set(shape.getXmlObject().copy());  
                        break; 
                    
                    default:  
                        //slide.getSpTree().addNewSp().set(tsh.getXmlObject().copy());  
                        // System.out.println("Copying placeholder : "+ ph);  
                        // slide.getXmlObject().getCSld().getSpTree().addNewSp().set(shape.getXmlObject().copy());  

                
                }  
        }
    }

    public void save() throws IOException {

        FileOutputStream out = null;
        try {
            System.out.println("Saving presentation to: " + targetPresentationFileName);
            out = new FileOutputStream(targetPresentationFileName);
            presentation.write(out);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }


    }

    public void emptySlide() {
        if (layout == null) {
            layout
                    = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
        }

        XSLFSlide slide = presentation.createSlide(layout);

        copyPlaceHoldersFromTemplate(this.titleSlide, slide);

        XSLFTextShape[] placeholders = slide.getPlaceholders();
        // System.out.println("New Slide placeholders before: ");
        // for (XSLFTextShape placeHold : placeholders) {
        //     System.out.println("    " + placeHold.getShapeId() + " " + placeHold.getShapeName() + " - " + placeHold.getText());  
        //     System.out.println("    ");
        // }
        
        // XSLFTextShape titleShape = slide.getPlaceholder(0);
        // XSLFTextShape contentShape = slide.getPlaceholder(1);

        // titleShape.setText("");
        // contentShape.clearText();

        for (XSLFTextShape placeHold : placeholders) {
            slide.removeShape(placeHold);

        }
        this.slideNumber++;
        
    }

    public void slide(String slideTitle, Content content, FontInfo fontInfo){
        if (content == null ) {
            emptySlide();
        } else {
            slideWithContent(slideTitle, content, fontInfo);
        }
    }

    public void slideWithContent(String slideTitle, Content content, FontInfo fontInfo) {


        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        if (layout == null) {
            layout
                    = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
        }

        XSLFSlide slide = presentation.createSlide(layout);

//        copyPlaceHoldersFromTemplate(this.titleSlide, slide);

        XSLFTextShape[] placeholders = slide.getPlaceholders();
//        System.out.println("New Slide placeholders before: ");
//        for (XSLFTextShape placeHold : placeholders) {
//            System.out.println("    " + placeHold.getShapeId() + " " + placeHold.getShapeName() + " - " + placeHold.getText());
//            System.out.println("    ");
//        }


//        for (XSLFShape shape : slide.getShapes()) {
//            if (shape instanceof XSLFAutoShape) {
//                // this is a template placeholder
//                System.out.println("    AUTO Shape: " + shape.getShapeId() + " " + shape.getShapeName());
//            }
//        }

         for (XSLFTextShape placeHold : placeholders) {
             System.out.println("    Removing placeHold: " + placeHold.getShapeId() + " " + placeHold.getShapeName());
             slide.removeShape(placeHold);

         }


        XSLFTextShape titleShape = slide.createTextBox();
        titleShape.setPlaceholder(Placeholder.TITLE);
        titleShape.setAnchor(this.getSlideTitleRectangle());

        XSLFTextShape contentShape = slide.createTextBox();
        contentShape.setPlaceholder(Placeholder.CONTENT);
        contentShape.setAnchor(this.getSlideContentRectangle());

        XSLFTextShape footerShape = slide.createTextBox();
        footerShape.setPlaceholder(Placeholder.FOOTER);
        footerShape.setAnchor(this.getSlideFooterRectangle());

        XSLFTextParagraph footerParagrapth = footerShape.addNewTextParagraph();

        footerParagrapth.setIndentLevel(0);
        footerParagrapth.setBullet(false);

        XSLFTextRun footerRun1 = footerParagrapth.addNewTextRun();
        footerRun1.setFontSize(this.getFooterFontInfo().getFontSize());
        footerRun1.setText(String.format(this.getFooterTemplate(), formatter.format(nowTime), this.slideNumber));

        titleShape.setText(slideTitle);

        switch (content.getContentType()) {
            case CODE: 
                addCodeText(contentShape, content.getText(), fontInfo);
                break; 
            case TEXT: 
                addText(contentShape, content.getText(), fontInfo);
                break; 
            case UL_LIST:
                addUlList(contentShape, content.getList(), fontInfo);
                break;
            case LI_LIST:
                addLiList(contentShape, content.getList(), fontInfo);
                break;
            case IMAGE:
                addImage(presentation, slide, content.getImage());
                break;
            
        }
        
        //this is just very beginning version with only text
        // contentShape.setText();

//        System.out.println("Slide placeholders after: ");
//        for (XSLFTextShape placeHold : placeholders) {
//            System.out.println("    " + placeHold.getShapeId() + " " + placeHold.getShapeName() + " - " + placeHold.getText());
//        }
        this.slideNumber++;
        
    }

    private void addImage(XMLSlideShow presentation, XSLFSlide slide, String imagePath) {
        byte[] pictureData = null;
        try {
            pictureData = IOUtils.toByteArray(
                    new FileInputStream(imagePath));

            BufferedImage bimg = ImageIO.read(new File(imagePath));
            int width          = bimg.getWidth();
            int height         = bimg.getHeight();
            double widthRatio = this.getSlideContentRectangle().getWidth() / width;
//            double heightRatio = this.getSlideContentRectangle().getHeight() / height;

            Rectangle anchor = new Rectangle(new Double(this.getSlideContentRectangle().getX()).intValue(),
                    new Double(this.getSlideContentRectangle().getY()).intValue(),
                    new Double(this.getSlideContentRectangle().getWidth()).intValue(),
                    new Double(height * widthRatio).intValue());

            XSLFPictureData pd
                    = presentation.addPicture(pictureData, PictureData.PictureType.PNG);
            XSLFPictureShape picture = slide.createPicture(pd);

            picture.setAnchor(anchor);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void addUlList(XSLFTextShape contentShape, List<String> ulList, FontInfo fontInfo) {
        contentShape.clearText();

        
        ulList.forEach(item -> {
            XSLFTextParagraph p1 = contentShape.addNewTextParagraph();
        
            p1.setIndentLevel(1);
            p1.setBullet(true);
            p1.setBulletCharacter("* ");

            XSLFTextRun r1 = p1.addNewTextRun();
            r1.setFontSize(fontInfo.getFontSize());
            r1.setText(item);
        });
        
    }


    private void addLiList(XSLFTextShape contentShape, List<String> liList, FontInfo fontInfo) {
        contentShape.clearText();
        int counter = 0;

        for (String item : liList) {
            counter++;
            XSLFTextParagraph p1 = contentShape.addNewTextParagraph();

            p1.setIndentLevel(1);
            p1.setBullet(true);
            p1.setBulletAutoNumber(AutoNumberingScheme.arabicParenRight, counter);

            XSLFTextRun r1 = p1.addNewTextRun();
            r1.setFontSize(fontInfo.getFontSize());
            r1.setText(item);
        };

    }

    private void addText(XSLFTextShape contentShape, String text, FontInfo fontInfo) {
        System.out.println("Old shape     " + contentShape.getShapeId() + " " + contentShape.getShapeName() + " - " + contentShape.getText()); 
        
        contentShape.clearText();
//        contentShape.setText(text);

        XSLFTextParagraph  p = contentShape.addNewTextParagraph();
        p.setBullet(false);

        XSLFTextRun r1 = p.addNewTextRun();

        r1.setText(text);
        r1.setFontSize(fontInfo.getFontSize());

        System.out.println("New shape TEXT    " + contentShape.getShapeId() + " " + contentShape.getShapeName() + " - " + contentShape.getText()); 
        

    }

    private void addCodeText(XSLFTextShape contentShape, String code, FontInfo fontInfo) {
        contentShape.clearText();
        // contentShape.setText("hmmmm ");
        contentShape.getTextParagraphs().forEach(tp-> {
            System.out.println("Empty Text Paragrapth "+ tp.getText());
        });
        System.out.println("Paragraphs size: "+ contentShape.getTextParagraphs().size());
        XSLFTextParagraph p = contentShape.addNewTextParagraph();
        p.setBullet(false);

        XSLFTextRun r1 = p.addNewTextRun();

        r1.setText(code);
        r1.setFontSize(fontInfo.getFontSize());
        r1.setFontColor(java.awt.Color.GRAY);
   

    }

    public void macOsSlide(String slideTitle, Content content) {
        if (layout == null) {
            layout
                    = defaultMaster.getLayout(SlideLayout.TEXT);
        }

        XSLFSlide slide = presentation.createSlide(layout);
        XSLFTextShape titleShape = slide.getPlaceholder(0);
        XSLFTextShape contentShape = slide.getPlaceholder(1);

        titleShape.setText(slideTitle);
        //this is just very beginning version with only text
        contentShape.setText(content.getText());

        copyPlaceHoldersFromTemplate(this.titleSlide, slide);

    }

    public void title(String thisIsTitle, String subtitleAuthorDate) {
        if (titleLayout == null) {
            titleLayout
                    = defaultMaster.getLayout(SlideLayout.TITLE);
        }

        titleSlide = presentation.getSlides().get(0);

        XSLFTextShape[] placeholders = titleSlide.getPlaceholders();
        System.out.println("Title Slide placeholders before: ");
        for (XSLFTextShape placeHold : placeholders) {
            System.out.println("    " + placeHold.getShapeId() + " " + placeHold.getShapeName() + " - " + placeHold.getText());
            System.out.println("    ");
        }


        // XSLFTextShape titleShape = titleSlide.getPlaceholder(0);
        // XSLFTextShape subTitleShape = titleSlide.getPlaceholder(1);



        XSLFTextShape titleShape = titleSlide.createTextBox();
        titleShape.setPlaceholder(Placeholder.TITLE);
        titleShape.setAnchor(this.getTitleRectangle());

        XSLFTextShape subTitleShape = titleSlide.createTextBox();
        subTitleShape.setPlaceholder(Placeholder.SUBTITLE);
        subTitleShape.setAnchor(this.getSubTitleRectangle());


        titleShape.setText(thisIsTitle);
        subTitleShape.setText(subtitleAuthorDate);

        this.slideNumber++;
    }

//    public void title(String thisIsTitle, String subtitleAuthorDate) {
//        title(thisIsTitle, new Rectangle(50, 50, 700, 100), subtitleAuthorDate, new Rectangle(200, 200, 500, 100));
//
//    }

    public void toPresentation(String targetPresentationFileName) {
        this.targetPresentationFileName = targetPresentationFileName;
    }
}
