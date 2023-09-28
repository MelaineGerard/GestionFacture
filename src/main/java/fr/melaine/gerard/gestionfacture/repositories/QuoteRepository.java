package fr.melaine.gerard.gestionfacture.repositories;


import fr.melaine.gerard.gestionfacture.entities.Invoice;
import fr.melaine.gerard.gestionfacture.entities.Quote;

import java.util.List;

public class QuoteRepository extends AbstractRepository {
    private static QuoteRepository instance;

    public static QuoteRepository getInstance() {
        if (instance == null) {
            instance = new QuoteRepository();
        }
        return instance;
    }

    public List<Quote> getQuotes() {
        return getDatabase().getQuotes();
    }
}
