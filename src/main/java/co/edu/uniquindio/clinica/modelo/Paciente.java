package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paciente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String correo;
    private Suscripcion suscripcion;

    @Override
    public String toString() {
        return "CC." + cedula +
                ", Paciente: " + nombre +
                ", Tipo suscripción: " + suscripcion.getTipoSuscripcion();
    }
}
