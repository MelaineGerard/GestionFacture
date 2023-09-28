package fr.melaine.gerard.gestionfacture.repositories;

import fr.melaine.gerard.gestionfacture.entities.Client;

import java.util.List;
import java.util.Optional;

public class ClientRepository extends AbstractRepository {
    private static ClientRepository instance;

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    public Client createClient(String fullname, String mailingAddress, String emailAddress, String phone) {
        Client client = new Client(fullname, mailingAddress, emailAddress, phone);
        getDatabase().addClient(client);

        return client;
    }

    public List<Client> getClients() {
        return getDatabase().getClients();
    }

    public Client getClientByKey(int key) {
        int realKey = key - 1;
        if (realKey < 0 || realKey >= getClients().size()) {
            return null;
        }

        return getClients().get(realKey);
    }
}
