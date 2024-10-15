package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.utils.AlertaUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroPacienteControlador extends AbstractControlador implements Initializable {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private ComboBox<TipoSuscripcion> cbSuscripcion;

    public void registrarPaciente(ActionEvent a) {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        TipoSuscripcion suscripcion = cbSuscripcion.getValue();
        try {
            getClinica().registrarPaciente(cedula,nombre,telefono,correo,suscripcion);
            AlertaUtil.mostrarAlerta("Paciente registrado con éxito", Alert.AlertType.INFORMATION);
            limpiarFormularioPaciente();

        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(),Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioPaciente() {
        txtCedula.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        cbSuscripcion.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSuscripcion.getItems().addAll(TipoSuscripcion.values());
    }
}

