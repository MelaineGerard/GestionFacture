package fr.melaine.gerard.gestionfacture;

import fr.melaine.gerard.gestionfacture.managers.MenuManager;

public class InvoiceManager {

    public static void main(String[] args) {
        while (true) {
            MenuManager.getInstance().displayMainMenu();
            int choice = MenuManager.getInstance().askForChoice();
            MenuManager.getInstance().handleMainMenu(choice);

            for (int i = 0 ; i < 5 ; i++) System.out.println();
        }
    }
}