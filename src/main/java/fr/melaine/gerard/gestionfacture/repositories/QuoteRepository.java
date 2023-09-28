package fr.melaine.gerard.gestionfacture.repositories;


import fr.melaine.gerard.gestionfacture.entities.*;

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

    public void createQuote(Client client, Enterprise enterprise, List<Product> products) {
        Quote quote = new Quote(client, enterprise, products);
        getDatabase().addQuote(quote);
    }
}
