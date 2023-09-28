package fr.melaine.gerard.gestionfacture.managers;

import fr.melaine.gerard.gestionfacture.entities.*;
import fr.melaine.gerard.gestionfacture.repositories.*;

import java.util.List;
import java.util.Scanner;

public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);

    public static MenuManager menuManager;

    public static MenuManager getInstance() {
        if (menuManager == null) {

            menuManager = new MenuManager();
        }

        return menuManager;
    }

    public void displayMainMenu() {
        System.out.println("1. Créer un client");
        System.out.println("2. Voir les clients");
        System.out.println("3. Créer une facture");
        System.out.println("4. Voir les factures");
        System.out.println("5. Créer un devis");
        System.out.println("6. Voir les devis");
        System.out.println("7. Modifier le profil entreprise");
        System.out.println("8. Voir le profil entreprise");
        System.out.println("9. Quitter");
    }

    public void handleMainMenu(int choice) {
        switch (choice) {
            case 1:
                createClient();
                break;
            case 2:
                showClients();
                break;
            case 3:
                createInvoice();
                break;
            case 4:
                showInvoices();
                break;
            case 5:
                createQuote();
                break;
            case 6:
                showQuotes();
                break;
            case 7:
                editEnterprise();
                break;
            case 8:
                showEnterprise();
                break;
            case 9:
                System.exit(0);
                break;
        }
    }

    private void showEnterprise() {
        Enterprise enterprise = EnterpriseRepository.getInstance().getEnterprise();

        System.out.println(enterprise);
    }

    private void editEnterprise() {
        System.out.println("Nom de l'entreprise :");
        String name = askForString();

        System.out.println("Adresse postale :");
        String mailingAddress = askForString();

        System.out.println("Adresse email :");
        String emailAddress = askForString();

        System.out.println("Numéro de téléphone :");
        String phoneNumber = askForString();

        EnterpriseRepository.getInstance().editEnterprise(name, mailingAddress, emailAddress, phoneNumber);

        System.out.println("Profil entreprise modifié");
    }

    private void showQuotes() {
        List<Quote> quotes = QuoteRepository.getInstance().getQuotes();

        if (quotes.isEmpty()) {
            System.out.println("Aucun devis n'a été créé");
            return;
        }

        System.out.println("Liste des devis :");

        for (Quote quote : quotes) {
            System.out.println(quote);
        }
    }

    private void createQuote() {
        if (ClientRepository.getInstance().getClients().isEmpty()) {
            System.out.println("Aucun client n'a été créé");
            return;
        }

        if (ProductRepository.getInstance().getProducts().isEmpty()) {
            System.out.println("Aucun produit n'a été créé");
            return;
        }

        showClients();
        System.out.println("Numéro du client :");
        int clientNumber = askForKey(ClientRepository.getInstance().getClients().size());

        showProducts();
        System.out.println("Numéro du produit :");
        int productNumber = askForKey(ProductRepository.getInstance().getProducts().size());

        Client client = ClientRepository.getInstance().getClients().get(clientNumber - 1);
        Product product = ProductRepository.getInstance().getProducts().get(productNumber - 1);
        Enterprise enterprise = EnterpriseRepository.getInstance().getEnterprise();

        QuoteRepository.getInstance().createQuote(client, enterprise, List.of(product));

        System.out.println("Devis créée");
    }

    private void showInvoices() {
        List<Invoice> invoices = InvoiceRepository.getInstance().getInvoices();

        if (invoices.isEmpty()) {
            System.out.println("Aucune factures n'a été créé");
            return;
        }

        System.out.println("Liste des factures :");

        for (Invoice invoice : invoices) {
            System.out.println(invoice);
        }
    }

    private void createInvoice() {
        if (ClientRepository.getInstance().getClients().isEmpty()) {
            System.out.println("Aucun client n'a été créé");
            return;
        }

        if (ProductRepository.getInstance().getProducts().isEmpty()) {
            System.out.println("Aucun produit n'a été créé");
            return;
        }

        showClients();
        System.out.println("Numéro du client :");
        int clientNumber = askForKey(ClientRepository.getInstance().getClients().size());

        showProducts();
        System.out.println("Numéro du produit :");
        int productNumber = askForKey(ProductRepository.getInstance().getProducts().size());

        Client client = ClientRepository.getInstance().getClients().get(clientNumber - 1);
        Product product = ProductRepository.getInstance().getProducts().get(productNumber - 1);
        Enterprise enterprise = EnterpriseRepository.getInstance().getEnterprise();

        InvoiceRepository.getInstance().createInvoice(client, enterprise, List.of(product));

        System.out.println("Facture créée");
    }

    private void showProducts() {
        List<Product> products = ProductRepository.getInstance().getProducts();

        if (products.isEmpty()) {
            System.out.println("Aucun produit n'a été créé");
            return;
        }

        System.out.println("Liste des produits :");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private int askForKey(int size) {
        int key = 0;
        while (key < 1 || key > size) {
            key = parseInt(scanner.nextLine());
        }

        return key;
    }

    private void showClients() {
        List<Client> clients = ClientRepository.getInstance().getClients();

        if (clients.isEmpty()) {
            System.out.println("Aucun client n'a été créé");
            return;
        }

        System.out.println("Liste des clients :");

        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void createClient() {
        System.out.println("Nom complet :");
        String fullname = askForString();

        System.out.println("Adresse postale :");
        String mailingAddress = askForString();

        System.out.println("Adresse email :");
        String emailAddress = askForString();

        System.out.println("Numéro de téléphone :");
        String phoneNumber = askForString();

        ClientRepository.getInstance().createClient(fullname, mailingAddress, emailAddress, phoneNumber);

        System.out.println("Client créé");
    }

    public int askForChoice() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while (choice < 1 || choice > 9) {
            choice = parseInt(scanner.nextLine());
        }

        return choice;
    }

    private int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String askForString() {
        String input = "";
        while (input.isEmpty()) {
            input = scanner.nextLine();
        }

        return input;
    }
}
