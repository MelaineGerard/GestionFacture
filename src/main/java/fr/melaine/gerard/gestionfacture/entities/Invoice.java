package fr.melaine.gerard.gestionfacture.entities;

import java.util.List;

public class Invoice extends Quote {
    public Invoice(Client client, Enterprise enterprise, List<Product> products) {
        super(client, enterprise, products);
    }

    @Override
    public String toString() {
        return """
                Facture :
                Client : %s
                Entreprise : %s
                Produits : %s
                """.formatted(
                        getClient().fullname(),
                getEnterprise().getName(),
                getProducts()
        );
    }
}
