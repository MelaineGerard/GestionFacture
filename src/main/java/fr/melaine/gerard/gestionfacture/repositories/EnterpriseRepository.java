package fr.melaine.gerard.gestionfacture.repositories;


import fr.melaine.gerard.gestionfacture.entities.Enterprise;

public class EnterpriseRepository extends AbstractRepository {
    private static EnterpriseRepository instance;

    public static EnterpriseRepository getInstance() {
        if (instance == null) {
            instance = new EnterpriseRepository();
        }
        return instance;
    }

    public Enterprise getEnterprise() {
        return getDatabase().getEnterprise();
    }
}
