package org.jacekkowalczyk82.c2p;


import org.jacekkowalczyk82.c2p.model.Presentation;
import org.jacekkowalczyk82.c2p.model.Slide;

import java.io.IOException;
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
                c2p.slide(s.getSlideTitle(), s.getContent(), s.getContentFontInfo());
            }

            try {
                c2p.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void usage() {
        System.out.println("Required parameters were not provided");
        System.out.println("Usage: code2present.bat <PATH TO INPUT JSON FILE>");
    }

}
