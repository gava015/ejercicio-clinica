package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionFactory;
import co.edu.uniquindio.clinica.utils.EnvioEmailUtil;
import co.edu.uniquindio.clinica.utils.FechaUtil;
import co.edu.uniquindio.clinica.utils.ValidacionUtil;
import javafx.scene.control.ComboBox;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Clinica {
    private List<Cita> listaCitas;
    private List<Servicio> listaServicios;
    private List<Paciente> listaPacientes;

    private ComboBox<Paciente> pacienteComboBox;

    public Clinica() {
        this.listaPacientes = new ArrayList<>();
        this.listaCitas = new ArrayList<>();
        this.listaServicios = new ArrayList<>();

        crearListaServicios();
    }

    public void registrarPaciente(String cedula, String nombre, String telefono,
                                  String correo, TipoSuscripcion tipoSuscripcion) throws Exception {

        validarDatosEntradaPaciente(cedula, nombre, telefono, correo, tipoSuscripcion);
        Suscripcion suscripcion = SuscripcionFactory.crearSuscripcion(tipoSuscripcion);

        Paciente paciente = new Paciente.PacienteBuilder()
                .cedula(cedula)
                .nombre(nombre)
                .telefono(telefono)
                .correo(correo)
                .suscripcion(suscripcion)
                .build();

        listaPacientes.add(paciente);
    }

    public void registrarCita(Paciente paciente, LocalDate fecha, String horaCita, Servicio servicio) throws Exception {

        validarDatosEntradaCita(paciente, fecha, horaCita, servicio);

        LocalDateTime fechaCita = LocalDateTime.of(fecha, LocalTime.parse(horaCita));

        for (Cita cita: listaCitas) {
            if (cita.getFecha().isEqual(fechaCita) && cita.getServicio().equals(servicio)) {
                throw new Exception("La fecha elegida no está disponible para el servicio de " + servicio.getNombre());
            }
        }

        Factura factura = paciente.getSuscripcion().generarFacturaCobro(servicio);
        Cita cita = Cita.builder()
                .paciente(paciente)
                .id(String.valueOf(UUID.randomUUID()))
                .fecha(fechaCita)
                .servicio(servicio)
                .factura(factura)
                .build();

        listaCitas.add(cita);

        String mensaje = paciente
                + "\nServicio: " + servicio.getNombre()
                + "\nFecha: " + cita.getFecha()
                + "\nSubtotal: $" + factura.getSubTotal()
                + "\nTotal: $" + factura.getTotal();

        EnvioEmailUtil.enviarNotificacion(paciente.getCorreo(), "Factura electrónica Clinica", mensaje);
    }

    public void cancelarCita(String citaId) {
        for (Cita cita: getListaCitas()) {
            if(cita.getId().equals(citaId)){
                listaCitas.remove(cita);
            }
        }
    }

    public void validarDatosEntradaPaciente(String cedula, String nombre, String telefono,
                                            String correo, TipoSuscripcion tipoSuscripcion) throws Exception {
        ValidacionUtil.validarCampo(cedula, "Cedula");
        ValidacionUtil.validarCampo(nombre, "Nombre");
        ValidacionUtil.validarCampo(telefono, "Telefono");
        ValidacionUtil.validarCampo(correo, "Correo");

        ValidacionUtil.validarCorreo(correo);
        ValidacionUtil.validarTelefono(telefono);
        ValidacionUtil.validarSuscripcion(tipoSuscripcion);

    }

    public void validarDatosEntradaCita(Paciente paciente, LocalDate fechaCita, String horaCita, Servicio servicio)
            throws Exception {

        ValidacionUtil.validarPaciente(paciente);
        ValidacionUtil.validarFecha(fechaCita);
        ValidacionUtil.validarHora(horaCita);
        ValidacionUtil.validarServicio(servicio);

    }

    public void crearListaServicios() {

        listaServicios.add(construirServicio("Medicina General", 20000));
        listaServicios.add(construirServicio("Odontología", 20000));
        listaServicios.add(construirServicio("Sicología", 40000));
        listaServicios.add(construirServicio("Nefrología", 60000));
        listaServicios.add(construirServicio("Cardiología", 60000));
        listaServicios.add(construirServicio("Neurología", 60000));
    }

    public Servicio construirServicio(String nombre, double precio) {
        return Servicio.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .nombre(nombre)
                .precio(precio)
                .build();
    }

    public List<String> getListaHoras() {
        return FechaUtil.crearListaHoras();
    }

}


