package org.jacekkowalczyk82.c2p;


import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfGraphics2D;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFBackground;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.jacekkowalczyk82.c2p.model.Presentation;
import org.jacekkowalczyk82.c2p.model.Slide;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JSON2Presentation {

    private static Logger LOGGER = LogManager.getLogger(JSON2Presentation.class);

    public static void main(String[] args) {

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

//            try {
//                FileInputStream iStream = new FileInputStream(new File(pres.getConfiguration().getTargetPresentationFile()));
//
//                XMLSlideShow presentation = new XMLSlideShow(iStream);
//                Dimension pgSize  = presentation.getPageSize();
                Dimension pgSize  = c2p.getPresentation().getPageSize();
                Document pdfDoc = new Document();
                double zoom = 1; // magnify it by 2 as typical slides are low res
                AffineTransform at = new AffineTransform();
                at.setToScale(zoom, zoom);

                try {
                    PdfWriter pdfWriter = PdfWriter.getInstance(pdfDoc,
                            new FileOutputStream(pres.getConfiguration().getTargetPresentationFile()+ ".pdf"));
                    pdfDoc.setPageSize(new Rectangle((float)pgSize.getWidth(), (float)pgSize.getHeight()));
                    pdfDoc.open();
//                    pdfDoc.add(new Chunk(""));

                    LOGGER.debug("Opening PDF Document");
                    for (XSLFSlide slide: c2p.getPresentation().getSlides()) {
                        LOGGER.debug("Adding slide "+ slide.getTitle());

                        BufferedImage bufImg = new BufferedImage((int)Math.ceil(pgSize.width*zoom),
                                (int)Math.ceil(pgSize.height*zoom), BufferedImage.TYPE_INT_RGB);
                        Graphics2D graphics = bufImg.createGraphics();
                        graphics.setTransform(at);
                        //clear the drawing area
                        XSLFBackground bcGround = slide.getBackground();
                        Color bgColor = null;
                        if (bcGround != null) {
                            bgColor = slide.getBackground().getFillColor();

                        }
                        if (bgColor!=null) {
                            graphics.setPaint(bgColor);
                        }
                        else {
                            graphics.setPaint(Color.white);
                        }


                        graphics.fill(new Rectangle2D.Float(0, 0, pgSize.width, pgSize.height));
                        try{
                            slide.draw(graphics);

                        } catch(Exception e){
                            //Just ignore, draw what I have
                            e.printStackTrace();
                        }

                        Image image = null;
                        try {
                            image = Image.getInstance(bufImg, null);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        pdfDoc.setPageSize(new Rectangle(image.getScaledWidth(), image.getScaledHeight()));
                        LOGGER.debug("At page "+ pdfDoc.getPageNumber());

//                        image.setAbsolutePosition(0, 0);
                        pdfDoc.add(image);

                        pdfDoc.newPage();
//
//                    PdfGraphics2D graphics  = (PdfGraphics2D) pdfWriter.getDirectContent().createGraphics((float)pgSize.getHeight(),
//                            (float) pgSize.getHeight());
//                    slide.draw(graphics);
//                    graphics.dispose();
                    }

                    pdfDoc.close();

                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }


//
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }


        }

    }

    private static void usage() {
        System.out.println("Required parameters were not provided");
        System.out.println("Usage: code2present.bat <PATH TO INPUT JSON FILE>");
    }

}
