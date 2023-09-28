package fr.melaine.gerard.gestionfacture.repositories;


import fr.melaine.gerard.gestionfacture.entities.Invoice;

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
}
