package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Cita {
    private Paciente paciente;
    private String id;
    private LocalDateTime fecha;
    private Servicio servicio;
    private Factura factura;
}
