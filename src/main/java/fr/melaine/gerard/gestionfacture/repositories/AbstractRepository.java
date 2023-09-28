package fr.melaine.gerard.gestionfacture.repositories;

public abstract class AbstractRepository {

    private final Database database;

    AbstractRepository() {
        database = Database.getInstance();
    }

    public Database getDatabase() {
        return database;
    }
}
