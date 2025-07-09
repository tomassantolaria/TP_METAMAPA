package Repositorio;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.*;

@Repository
public class HechoRepositorio {
    private final Map<UUID, Hecho> hechos = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Hecho> getHechos() {
        return new ArrayList<>(hechos.values());
    }

    public void eliminarHecho(UUID id) { this.hechos.get(id).setVisible(false); }
        //this.hechos.remove(id); }

    public void agregarHecho(Hecho hecho) {
        if (hecho != null && !hechos.containsKey(hecho.getId())) {
            hechos.put(hecho.getId(), hecho);
            hecho.setVisible(true);
        }
    }

    public Hecho buscarHechoPorId(UUID id) {
        return this.hechos.get(id);
    }

    public Boolean cantidadFuentesConTitulo (String titulo, Set <Integer> fuentes) {

        for (Hecho hecho : hechos.values()) {
            if(hecho.titulo.equals(titulo) && !fuentes.contains(hecho.idFuente)) {
               return false;
            }
        }
        return true;
    }

    public  Set <Integer> cantidadFuentesConHecho (Hecho hecho) {

        Set <Integer> fuentes = new HashSet<>();
        for (Hecho hecho1 : hechos.values()) {
            if(hecho1.esIgualA(hecho)) {
                fuentes.add(hecho.getIdFuente());
            }
        }
        return fuentes;
    }

    public List<Hecho> hechosConFuente (String fuente) {
        return hechos.values().stream().filter(hecho -> hecho.getOrigen_carga().name().equals(fuente) && hecho.isVisible()).toList();
    }
}
