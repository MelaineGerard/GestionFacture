module fr.melaine.gerard.gestionfacture.gestionfacture {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.melaine.gerard.gestionfacture.gestionfacture to javafx.fxml;
    exports fr.melaine.gerard.gestionfacture.gestionfacture;
}