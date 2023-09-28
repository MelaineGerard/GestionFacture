package fr.melaine.gerard.gestionfacture;

import fr.melaine.gerard.gestionfacture.managers.MenuManager;
import fr.melaine.gerard.gestionfacture.repositories.ProductRepository;

public class InvoiceManager {

    public static void main(String[] args) {
        // TODO: Ajouter la création de produit dans le menu
        ProductRepository.getInstance().createProduct("Pomme", 1.0f);
        ProductRepository.getInstance().createProduct("Poire", 1.5f);
        ProductRepository.getInstance().createProduct("Banane", 2.0f);
        ProductRepository.getInstance().createProduct("Orange", 2.5f);


        while (true) {
            MenuManager.getInstance().displayMainMenu();
            int choice = MenuManager.getInstance().askForChoice();
            MenuManager.getInstance().handleMainMenu(choice);

            for (int i = 0 ; i < 5 ; i++) System.out.println();
        }
    }
}