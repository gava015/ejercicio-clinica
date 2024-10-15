package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class SuscripcionBasica implements Suscripcion {

    @Override
    public TipoSuscripcion getTipoSuscripcion() {
        return TipoSuscripcion.BASICA;
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return List.of(
                Servicio.builder()
                        .id(String.valueOf(UUID.randomUUID()))
                        .precio(0.0)
                        .nombre("Sicología")
                        .build(),
                Servicio.builder()
                        .id(String.valueOf(UUID.randomUUID()))
                        .precio((double)40000)
                        .nombre("Nefrología")
                        .build(),
                Servicio.builder()
                        .id(String.valueOf(UUID.randomUUID()))
                        .precio((double)40000)
                        .nombre("Cardiología")
                        .build()
        );
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicioSeleccionado) {

        Servicio servicio = servicioSeleccionado;
        for (Servicio servicioDisponible: getServiciosDisponibles()) {
            if(servicioDisponible.getNombre().equalsIgnoreCase(servicioSeleccionado.getNombre())) {
                servicio = servicioDisponible;
                break;
            }
        }

        return Factura.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .fecha(LocalDate.now())
                .subTotal(servicioSeleccionado.getPrecio())
                .total(servicio.getPrecio())
                .build();
    }

}
