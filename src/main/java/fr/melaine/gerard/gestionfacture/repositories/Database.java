package fr.melaine.gerard.gestionfacture.repositories;

import fr.melaine.gerard.gestionfacture.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Database {
    // TODO: Passer à une base de données SQL
    private static Database instance;
    private final List<Client> clients;
    private final List<Quote> quotes;
    private final List<Invoice> invoices;
    private final List<Product> products;
    private final Enterprise enterprise;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public Database() {
        this.clients = new ArrayList<>();
        this.quotes = new ArrayList<>();
        this.invoices = new ArrayList<>();
        this.products = new ArrayList<>();
        this.enterprise = new Enterprise.Builder().build();
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Quote> getQuotes() {
        return quotes;
    }
    public void addQuote(Quote quote) {
        quotes.add(quote);
    }
}
