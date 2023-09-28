package fr.melaine.gerard.gestionfacture.entities;

import java.util.List;

public class Quote {

    private final Client client;
    private final Enterprise enterprise;
    private final List<Product> products;

    public Quote(Client client, Enterprise enterprise, List<Product> products) {
        this.client = client;
        this.enterprise = enterprise;
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return """
                Devis :
                Client : %s
                Entreprise : %s
                Produits : %s
                """.formatted(
                client,
                enterprise,
                products
        );
    }
}
