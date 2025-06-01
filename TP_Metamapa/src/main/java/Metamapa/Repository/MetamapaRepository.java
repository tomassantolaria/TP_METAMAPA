package Metamapa.Repository;

import Domain.Contribuyente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MetamapaRepository {
    private final List<Contribuyente> contribuyentes = new ArrayList<>();

    public void agregarContribuyente(Contribuyente contribuyente) {
        contribuyentes.add(contribuyente);
    }

    public List<Contribuyente> getContribuyentes() {return contribuyentes;}
}
