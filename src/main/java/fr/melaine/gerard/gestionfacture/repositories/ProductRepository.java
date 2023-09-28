package fr.melaine.gerard.gestionfacture.repositories;

import fr.melaine.gerard.gestionfacture.entities.Product;

import java.util.List;

public class ProductRepository extends AbstractRepository {
    private static ProductRepository instance;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void createProduct(String name, float price) {
        Product product = new Product(name, price);
        getDatabase().addProduct(product);
    }

    public List<Product> getProducts() {
        return getDatabase().getProducts();
    }
}
