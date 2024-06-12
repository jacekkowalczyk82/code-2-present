package org.jacekkowalczyk82.c2p;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePDF {
    public static void main(String[] args) {
        // Step 1: Create a Document object
        Document document = new Document();

        try {
            // Step 2: Create a PdfWriter instance and associate it with the Document
            PdfWriter.getInstance(document, new FileOutputStream("example.pdf"));

            // Step 3: Open the Document
            document.open();

            // Step 4: Add content to the Document
            document.add(new Paragraph("Hello, World!"));

            // Adding an image
            String imageUrl = "example_images/github.png";
            Image image = Image.getInstance(imageUrl);
            document.add(image);

            // Adding another page
            document.newPage();
            document.add(new Paragraph("This is a new page."));

            // Adding an image on the second page
            document.add(Image.getInstance(imageUrl));

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            // Step 5: Close the Document
            document.close();
        }
    }
}

