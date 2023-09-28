package fr.melaine.gerard.gestionfacture.entities;

import java.util.List;

public record Invoice(Client client, Enterprise enterprise, List<Product> products) {
    @Override
    public String toString() {
        return """
                Facture :
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
