package Domain;
import java.util.List;
import java.util.stream.Collectors;

public class Organizador {
    private List<Filtro> filtros;
    private Criterios criterios;

    public Organizador(List<Filtro> filtros, Criterios criterios){
        this.filtros = filtros;
        this.criterios = criterios;
    }

    public List<Hecho> filtrar(List<Hecho> hechos) {
        return hechos.stream().filter(h -> filtros.stream().allMatch(f->f.cumple(h, criterios))).collect(Collectors.toList());
    }



}
