package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.utils.AlertaUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListaCitasControlador extends AbstractControlador implements Initializable {

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private TableColumn<Cita, String> colServicio;

    private ObservableList<Cita> observableList;

    public void cancelarCita() {

        try {
            Cita cita = tablaCitas.getSelectionModel().getSelectedItem();
            if (cita != null) {
                clinica.cancelarCita(cita.getId());
                AlertaUtil.mostrarAlerta("Cita cancelada con éxito", Alert.AlertType.INFORMATION);
                actualizarTabla();
            } else {
                AlertaUtil.mostrarAlerta("No se ha seleccionado ninguna cita para cancelar", Alert.AlertType.WARNING);
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().toString()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().toString()));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaCitas.setItems(observableList);
    }

    @Override
    protected void inicializarClinica(Clinica clinica) {
        this.clinica = clinica;
        actualizarTabla();
    }

    public void actualizarTabla() {
        observableList.setAll(getClinica().getListaCitas());
    }
}
