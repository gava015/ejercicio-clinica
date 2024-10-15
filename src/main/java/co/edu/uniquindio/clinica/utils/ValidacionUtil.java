package co.edu.uniquindio.clinica.utils;

import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public  class ValidacionUtil {

    public static void validarCorreo(String correo) throws Exception{
        if (!Pattern.matches("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}", correo)) {
            throw new Exception("El correo no tiene una estructura correcta");
        }
    }


    public static void validarTelefono(String telefono) throws Exception {
        if (!Pattern.matches("[0-9]*", telefono)) {
            throw new Exception("El teléfono no es válido");

        }
    }

    public static void validarCampo(String campo, String nombreCampo) throws Exception {
        if (campo == null || campo.trim().isEmpty()) {
            throw new Exception("El campo " + nombreCampo + " es obligatorio.");
        }
    }

    public static void validarSuscripcion(TipoSuscripcion suscripcion) throws Exception {
        if (suscripcion == null) {
            throw new Exception("Debe seleccionar un tipo de suscripción");
        }
    }

    public static void validarFecha(LocalDate fecha) throws Exception {
        if (fecha == null ) {
            throw new Exception("Debe selecionar la fecha");
        }

        if (fecha.isBefore(LocalDate.now())) {
            throw new Exception("Fecha inválida");
        }
    }

    public static void validarHora(String hora) throws Exception {
        if (hora == null) {
            throw new Exception("Debe ingresar una hora en formato HH:mm.");
        }
    }

    public static void validarServicio(Servicio servicio) throws Exception {
        if (servicio == null){
            throw new Exception("Debe seleccionar un servicio");
        }
    }

    public static void validarPaciente(Paciente paciente) throws Exception {
        if (paciente == null){
            throw new Exception("Debe seleccionar un paciente");
        }
    }
}
