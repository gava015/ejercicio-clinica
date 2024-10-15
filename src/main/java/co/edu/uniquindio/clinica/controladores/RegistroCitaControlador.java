package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.utils.AlertaUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistroCitaControlador extends AbstractControlador implements Initializable {

    @FXML
    private ComboBox<Paciente> cbPaciente;

    @FXML
    private DatePicker dpFechaCita;

    @FXML
    private ComboBox<String> cbHora;

    @FXML
    private ComboBox<Servicio> cbServicio;

    @Override
    protected void inicializarClinica(Clinica clinica){
        this.clinica = clinica;

        cbPaciente.setItems(FXCollections.observableArrayList(getClinica().getListaPacientes()));
        cbServicio.setItems(FXCollections.observableArrayList(clinica.getListaServicios()));
        cbHora.setItems(FXCollections.observableArrayList(clinica.getListaHoras()));
    }

    public void registrarCita() {
        Paciente paciente = cbPaciente.getValue();
        LocalDate fecha = dpFechaCita.getValue();
        String hora = cbHora.getValue();
        Servicio servicio = cbServicio.getValue();

        try {
            getClinica().registrarCita(paciente, fecha, hora, servicio);
            AlertaUtil.mostrarAlerta("Cita creada con éxito", Alert.AlertType.INFORMATION);
            limpiarFormularioCita();

        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioCita() {
        cbPaciente.setValue(null);
        dpFechaCita.setValue(null);
        cbServicio.setValue(null);
        cbHora.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
