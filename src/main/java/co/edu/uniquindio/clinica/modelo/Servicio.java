package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servicio {

    private String id;
    private Double precio;
    private String nombre;

    @Override
    public String toString() {
        return nombre;
    }
}
