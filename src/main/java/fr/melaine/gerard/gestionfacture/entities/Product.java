package fr.melaine.gerard.gestionfacture.entities;

public record Product(String name, float price) {
    @Override
    public String toString() {
        return name + " - " + price + "€";
    }
}
