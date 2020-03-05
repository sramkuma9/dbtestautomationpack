package bni.regression.libraries.common;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadPDFReader {
    private String pdfText;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    public String readPDF() throws IOException
    {
        PDDocument document = PDDocument.load(new File(readWritePropertyFile.loadAndReadPropertyFile("pdfFilePath", "properties/config.properties")));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            pdfText = stripper.getText(document);
            System.out.println(pdfText);
        }
        document.close();
        return pdfText;
    }
}