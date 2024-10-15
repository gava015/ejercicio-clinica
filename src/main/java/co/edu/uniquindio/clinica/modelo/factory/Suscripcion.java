package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;

import java.util.List;

public interface Suscripcion {

    TipoSuscripcion getTipoSuscripcion();

    List<Servicio> getServiciosDisponibles();

    Factura generarFacturaCobro(Servicio servicio);

}
