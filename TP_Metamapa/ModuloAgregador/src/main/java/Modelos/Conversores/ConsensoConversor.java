package Modelos.Conversores;

import Modelos.Entidades.Consenso.Consenso;
import Modelos.Entidades.Consenso.ConsensoAbsoluta;
import Modelos.Entidades.Consenso.ConsensoMayoriaSimple;
import Modelos.Entidades.Consenso.ConsensoMultiplesMenciones;
import jakarta.persistence.AttributeConverter;

public class ConsensoConversor implements AttributeConverter<Consenso, String> {
    @Override
    public String convertToDatabaseColumn(Consenso consenso) {
        if (consenso instanceof ConsensoAbsoluta ) {
            return "Absoluta";
        } else if (consenso instanceof ConsensoMultiplesMenciones ) {
            return "MultiplesMenciones";
        } else if (consenso instanceof ConsensoMayoriaSimple){
            return "MayoriaSimple";
        }
        return null;

    }
    @Override
    public Consenso convertToEntityAttribute(String consenso) {
        Consenso consenso1 = null;
        if (consenso.equals("Absoluta")) {
            consenso1 = new ConsensoAbsoluta();
        } else if (consenso.equals("MultiplesMenciones")) {
            consenso1 =  new ConsensoMultiplesMenciones();
        } else if (consenso.equals("MayoriaSimple")) {
            consenso1 =  new ConsensoMayoriaSimple();
        } else {
            throw new IllegalArgumentException("Tipo desconocido: " + consenso);
        }
        return consenso1;

        // CUANDO SE USA EL CONSENSO SE LE DEBE SETAR EL REPOSITORIO
        // VER COMO MEJORAR
    }
}
