package fr.melaine.gerard.gestionfacture.managers;

import fr.melaine.gerard.gestionfacture.entities.Client;
import fr.melaine.gerard.gestionfacture.entities.Enterprise;
import fr.melaine.gerard.gestionfacture.entities.Invoice;
import fr.melaine.gerard.gestionfacture.entities.Quote;
import fr.melaine.gerard.gestionfacture.repositories.*;

import java.util.List;
import java.util.Scanner;

public class MenuManager {

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
}
