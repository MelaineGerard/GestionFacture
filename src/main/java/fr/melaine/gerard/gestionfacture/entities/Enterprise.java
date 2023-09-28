package fr.melaine.gerard.gestionfacture.entities;

public class Enterprise {
    private String name;
    private String mailingAddress;
    private String emailAddress;
    private String phone;

    public Enterprise(Enterprise.Builder builder) {
        this.name = builder.name;
        this.mailingAddress = builder.mailingAddress;
        this.emailAddress = builder.emailAddress;
        this.phone = builder.phone;
    }

    public static class Builder {
        private String name;
        private String mailingAddress;
        private String emailAddress;
        private String phone;

        public Enterprise.Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Enterprise.Builder setMailingAddress(String mailingAddress) {
            this.mailingAddress = mailingAddress;

            return this;
        }

        public Enterprise.Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;

            return this;
        }

        public Enterprise.Builder setPhone(String phone) {
            this.phone = phone;

            return this;
        }
        public Enterprise build(){
            return new Enterprise(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return """
                Nom de l'entreprise : %s
                Adresse postale : %s
                Adresse email : %s
                Téléphone : %s
                """.formatted(getName(), getMailingAddress(), getEmailAddress(), getPhone());
    }
}
