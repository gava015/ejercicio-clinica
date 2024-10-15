package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListaPacientesControlador extends AbstractControlador implements Initializable {

    @FXML
    private TableColumn<Paciente, String> colCedula;

    @FXML
    private TableColumn<Paciente, String> colNombre;

    @FXML
    private TableColumn<Paciente, String> colTelefono;

    @FXML
    private TableColumn<Paciente, String> colCorreo;

    @FXML
    private TableColumn<Paciente, String> colSuscripcion;

    @FXML
    private TableView<Paciente> tablaPacientes;

    private ObservableList<Paciente> observableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        colSuscripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuscripcion().getTipoSuscripcion().toString()));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaPacientes.setItems(observableList);
    }

    @Override
    protected void inicializarClinica(Clinica clinica){
        this.clinica = clinica;
        actualizarTabla();
    }

    public void actualizarTabla() {
        observableList.setAll(getClinica().getListaPacientes());
    }
}
