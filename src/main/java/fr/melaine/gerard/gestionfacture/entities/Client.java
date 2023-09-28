package fr.melaine.gerard.gestionfacture.entities;

public record Client(String fullname, String mailingAddress, String emailAddress, String phone) {
    @Override
    public String toString() {
        return """
                Nom du client : %s
                Adresse postale : %s
                Adresse email : %s
                Téléphone : %s
                """.formatted(fullname, mailingAddress, emailAddress, phone);
    }
}
