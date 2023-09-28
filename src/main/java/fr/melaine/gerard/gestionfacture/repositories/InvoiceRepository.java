package fr.melaine.gerard.gestionfacture.repositories;


import fr.melaine.gerard.gestionfacture.entities.Client;
import fr.melaine.gerard.gestionfacture.entities.Enterprise;
import fr.melaine.gerard.gestionfacture.entities.Invoice;
import fr.melaine.gerard.gestionfacture.entities.Product;

import java.util.List;

public class InvoiceRepository extends AbstractRepository {
    private static InvoiceRepository instance;

    public static InvoiceRepository getInstance() {
        if (instance == null) {
            instance = new InvoiceRepository();
        }
        return instance;
    }

    public List<Invoice> getInvoices() {
        return getDatabase().getInvoices();
    }

    public void createInvoice(Client client, Enterprise enterprise, List<Product> products) {
        Invoice invoice = new Invoice(client, enterprise, products);
        getDatabase().addInvoice(invoice);
    }
}
