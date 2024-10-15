package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.enums.TipoSuscripcion;

public class SuscripcionFactory {

    public static Suscripcion crearSuscripcion(TipoSuscripcion tipoSuscripcion) {

        switch (tipoSuscripcion) {
            case PREMIUM:
                return new SuscripcionPremium();
            case FAMILIAR:
                return new SuscripcionFamiliar();
            default:
                return new SuscripcionBasica();
        }
    }
}
