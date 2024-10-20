package co.edu.uniquindio.clinica.controladores;


import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


public class PanelControlador extends AbstractControlador {

    @FXML
    private StackPane panelPrincipal;

    private final Clinica clinica;


    public PanelControlador() {
        this.clinica = new Clinica(); // Se crea una única instancia de la clase Clinica
    }


    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroPaciente.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaPacientes(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaPacientes.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarRegistroCita(ActionEvent actionEvent) {

        Parent node = cargarPanel("/registroCita.fxml");

        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaCitas(ActionEvent actionEvent) {

        Parent node = cargarPanel("/listaCitas.fxml");

        panelPrincipal.getChildren().setAll(node);
    }

    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            ((AbstractControlador) loader.getController()).inicializarClinica(clinica);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
