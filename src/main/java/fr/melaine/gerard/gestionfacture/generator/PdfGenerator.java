package fr.melaine.gerard.gestionfacture.generator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.melaine.gerard.gestionfacture.entities.Invoice;
import fr.melaine.gerard.gestionfacture.entities.Product;
import fr.melaine.gerard.gestionfacture.entities.Quote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

// TODO: Refacto de la classe pour éviter la duplication de code (Modifier les entité Quote et Invoice pour qu'elles héritent d'une classe abstraite)
public class PdfGenerator {
    private final String path = new File("./pdf").getAbsolutePath() + File.separator;
    private static PdfGenerator instance;


    public static PdfGenerator getInstance() {
        if (instance == null && new File("./pdf").mkdirs()) {
            instance = new PdfGenerator();
        }
        return instance;
    }

    public void generate(Quote quote) {
        String filename = "devis_" + quote.client().fullname() + "_" + quote.enterprise().getName() + "_" +  UUID.randomUUID() + ".pdf";
        String fullPath = path + filename;

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();
            document.addTitle("Devis");
            document.addSubject("Devis du client %s" .formatted(quote.client().fullname()));
            document.addAuthor(quote.enterprise().getName());
            document.addCreator(quote.enterprise().getName());

            generateContent(document, quote);

            document.close();
            System.out.println("Devis exporté en PDF - Nom du fichier : " + filename);
        } catch (IOException | DocumentException e) {
            System.out.println("Erreur lors de la génération du PDF : " + e.getMessage());
        }
    }

    private void generateContent(Document document, Quote quote) {
        float total = 0;
        Paragraph enterpriseName = new Paragraph(quote.enterprise().getName());
        enterpriseName.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterpriseMailingAddress = new Paragraph(quote.enterprise().getMailingAddress());
        enterpriseMailingAddress.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterpriseEmailAddress = new Paragraph(quote.enterprise().getEmailAddress());
        enterpriseEmailAddress.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterprisePhone = new Paragraph(quote.enterprise().getPhone());
        enterprisePhone.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph clientName = new Paragraph(quote.client().fullname());
        clientName.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientMailingAddress = new Paragraph(quote.client().mailingAddress());
        clientMailingAddress.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientEmailAddress = new Paragraph(quote.client().emailAddress());
        clientEmailAddress.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientPhone = new Paragraph(quote.client().phone());
        clientPhone.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph date = new Paragraph(new Date().toString());
        date.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph invoiceP = new Paragraph("Devis");
        invoiceP.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(3);
        table.addCell("Produit");
        table.addCell("Quantité");
        table.addCell("Prix");

        for (Product product : quote.products()) {
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


    private void generateContent(Document document, Invoice invoice) {
        float total = 0;
        Paragraph enterpriseName = new Paragraph(invoice.enterprise().getName());
        enterpriseName.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterpriseMailingAddress = new Paragraph(invoice.enterprise().getMailingAddress());
        enterpriseMailingAddress.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterpriseEmailAddress = new Paragraph(invoice.enterprise().getEmailAddress());
        enterpriseEmailAddress.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph enterprisePhone = new Paragraph(invoice.enterprise().getPhone());
        enterprisePhone.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph clientName = new Paragraph(invoice.client().fullname());
        clientName.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientMailingAddress = new Paragraph(invoice.client().mailingAddress());
        clientMailingAddress.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientEmailAddress = new Paragraph(invoice.client().emailAddress());
        clientEmailAddress.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph clientPhone = new Paragraph(invoice.client().phone());
        clientPhone.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph date = new Paragraph(new Date().toString());
        date.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph invoiceP = new Paragraph("Facture");
        invoiceP.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(3);
        table.addCell("Produit");
        table.addCell("Quantité");
        table.addCell("Prix");

        for (Product product : invoice.products()) {
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


    public void generate(Invoice invoice) {
        String filename = "facture_" + invoice.client().fullname() + "_" + invoice.enterprise().getName() + "_" +  UUID.randomUUID() + ".pdf";
        String fullPath = path + filename;

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();
            document.addTitle("Facture");
            document.addSubject("Facture du client %s" .formatted(invoice.client().fullname()));
            document.addAuthor(invoice.enterprise().getName());
            document.addCreator(invoice.enterprise().getName());

            generateContent(document, invoice);

            document.close();
            System.out.println("Facture exporté en PDF - Nom du fichier : " + filename);
        } catch (IOException | DocumentException e) {
            System.out.println("Erreur lors de la génération du PDF : " + e.getMessage());
        }
    }
}
