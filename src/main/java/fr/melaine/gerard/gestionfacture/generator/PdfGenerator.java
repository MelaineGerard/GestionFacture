package fr.melaine.gerard.gestionfacture.generator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.melaine.gerard.gestionfacture.entities.Product;
import fr.melaine.gerard.gestionfacture.entities.Quote;
import fr.melaine.gerard.gestionfacture.enumerations.QuoteType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class PdfGenerator {
    private final String path = new File("./pdf").getAbsolutePath() + File.separator;
    private static PdfGenerator instance;


    public static PdfGenerator getInstance() {
        if (instance == null && new File("./pdf").mkdirs()) {
            instance = new PdfGenerator();
        }
        return instance;
    }

    public void generate(Quote quote, QuoteType quoteType) {
        String filename = "devis_" + quote.getClient().fullname() + "_" + quote.getEnterprise().getName() + "_" +  UUID.randomUUID() + ".pdf";
        String fullPath = path + filename;

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();
            document.addTitle(quoteType == QuoteType.QUOTE ? "Devis" : "Facture");
            document.addSubject(quoteType == QuoteType.QUOTE ? "Devis du client %s".formatted(quote.getClient().fullname()) : "Facture du client %s".formatted(quote.getClient().fullname()));
            document.addAuthor(quote.getEnterprise().getName());
            document.addCreator(quote.getEnterprise().getName());

            generateContent(document, quote, quoteType);

            document.close();
            System.out.printf("%s exporté en PDF - Nom du fichier : %s%n", (quoteType == QuoteType.QUOTE ? "Devis" : "Facture"), filename);
        } catch (IOException | DocumentException e) {
            System.out.println("Erreur lors de la génération du PDF : " + e.getMessage());
        }
    }

    private void generateContent(Document document, Quote quote, QuoteType quoteType) {
        float total = 0;
        Paragraph enterpriseName = new Paragraph(quote.getEnterprise().getName());
        enterpriseName.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterpriseMailingAddress = new Paragraph(quote.getEnterprise().getMailingAddress());
        enterpriseMailingAddress.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterpriseEmailAddress = new Paragraph(quote.getEnterprise().getEmailAddress());
        enterpriseEmailAddress.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterprisePhone = new Paragraph(quote.getEnterprise().getPhone());
        enterprisePhone.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph clientName = new Paragraph(quote.getClient().fullname());
        clientName.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientMailingAddress = new Paragraph(quote.getClient().mailingAddress());
        clientMailingAddress.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientEmailAddress = new Paragraph(quote.getClient().emailAddress());
        clientEmailAddress.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientPhone = new Paragraph(quote.getClient().phone());
        clientPhone.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph date = new Paragraph(new Date().toString());
        date.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph invoiceP = new Paragraph(quoteType == QuoteType.QUOTE ? "Devis" : "Facture");
        invoiceP.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(3);
        table.addCell("Produit");
        table.addCell("Quantité");
        table.addCell("Prix");

        for (Product product : quote.getProducts()) {
            table.addCell(product.name());
            table.addCell(String.valueOf(1));
            table.addCell(product.price() + "€");

            total += product.price();
        }

        Paragraph totalParagraph = new Paragraph("Total : " + total + "€");
        totalParagraph.setAlignment(Paragraph.ALIGN_RIGHT);

        try {
            document.add(enterpriseName);
            document.add(enterpriseMailingAddress);
            document.add(enterpriseEmailAddress);
            document.add(enterprisePhone);
            document.add(clientName);
            document.add(clientMailingAddress);
            document.add(clientEmailAddress);
            document.add(clientPhone);
            document.add(invoiceP);
            document.add(date);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(table);
            document.add(totalParagraph);
        } catch (DocumentException e) {
            System.out.println("Erreur lors de la génération du contenu du PDF : " + e.getMessage());
        }
    }
}
