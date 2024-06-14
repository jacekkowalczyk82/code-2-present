package org.jacekkowalczyk82.c2p;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.jacekkowalczyk82.c2p.model.Presentation;
import org.jacekkowalczyk82.c2p.model.Slide;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;

public class JSON2Presentation {

    private static Logger LOGGER = LogManager.getLogger(JSON2Presentation.class);

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            usage();
        }
        else {
            Presentation pres = JSONTools.readJSONToPresentation(args[0]);


            Code2Present c2p = new Code2Present();
            LOGGER.info("Generating presentation "+ pres.getConfiguration().getTargetPresentationFile());

            c2p.fromTemplate(pres.getConfiguration().getFromTemplate());
            c2p.toPresentation(pres.getConfiguration().getTargetPresentationFile());
            c2p.setTitleRectangle(pres.getConfiguration().getTitleRectangle().toAWTRectangle());
            c2p.setSubTitleRectangle(pres.getConfiguration().getSubTitleRectangle().toAWTRectangle());

            c2p.setSlideTitleRectangle(pres.getConfiguration().getSlideTitleRectangle().toAWTRectangle());
            c2p.setSlideContentRectangle(pres.getConfiguration().getSlideContentRectangle().toAWTRectangle());
            c2p.setSlideFooterRectangle(pres.getConfiguration().getSlideFooterRectangle().toAWTRectangle());
            c2p.setFooterFontInfo(pres.getConfiguration().getFooterFontInfo());
            c2p.setFooterTemplate(pres.getConfiguration().getFooterTemplate());

            c2p.title(pres.getTitleSlide().getTitle(), pres.getTitleSlide().getSubTitle());

            for (Slide s: pres.getSlides()) {
                c2p.slide(s.getSlideTitle(), s.getContent(), s.getContentStyleInfo());
            }

            try {
                c2p.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //save to PDF

            String outputFilePath = "presentation.pdf";

            // Load the PPTX file
//            String inputFilePath = "presentation.pptx";
//            XMLSlideShow ppt = new XMLSlideShow(
//                    new FileInputStream(pres.getConfiguration().getTargetPresentationFile()));
            XMLSlideShow ppt = c2p.getPresentation();
            // Create a new PDF document
            try (PDDocument document = new PDDocument()) {
                int slideIndex = 0;
                for (XSLFSlide slide : ppt.getSlides()) {
                    slideIndex++;
                    System.out.println("Rendering slide " + slideIndex);

                    // Get the slide dimensions
                    Dimension slideBounds = slide.getSlideShow().getPageSize();
                    int width = (int) slideBounds.getWidth();
                    int height = (int) slideBounds.getHeight();

                    // Create a BufferedImage for drawing the slide with transparency
                    BufferedImage slideImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics = slideImage.createGraphics();

                    // Set rendering hints for better quality
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

                    // Fill the background with white color (or transparent if needed)
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, width, height);

                    // Draw the slide onto the BufferedImage
                    try {
                        slide.draw(graphics);
                    } catch (Exception e) {
                        System.err.println("Error rendering slide " + slideIndex + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                    graphics.dispose();



                    // Save the BufferedImage to a temporary file
                    File tempFile = File.createTempFile("slide", ".png");
                    ImageIO.write(slideImage, "png", tempFile);

                    // Convert the temporary file to a PDImageXObject
                    PDImageXObject pdImage = PDImageXObject.createFromFile(tempFile.getAbsolutePath(), document);

                    // Create a new page in the PDF document
                    PDPage page = new PDPage(new PDRectangle(width, height));
                    document.addPage(page);

                    // Draw the image on the PDF page
                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                        contentStream.drawImage(pdImage, 0, 0, width, height);
                    }

                    // Delete the temporary file
                    tempFile.delete();

                    System.out.println("Slide " + slideIndex + " rendered successfully");

                }
                // Save the PDF document
                document.save(new FileOutputStream(outputFilePath));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("PPTX converted to PDF successfully.");


        }

    }

    private static void usage() {
        System.out.println("Required parameters were not provided");
        System.out.println("Usage: code2present.bat <PATH TO INPUT JSON FILE>");
    }

}
