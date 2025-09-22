package Repositorios;
import Modelos.Entidades.HechoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HechoDemoRepositorio extends JpaRepository<HechoDTO, Long> {
    List<HechoDTO> findByUrlFuente(String urlFuente);
}

// @Repository
// public class FuenteDemo_Hechos {

//     private final Map<String, List<HechoDTO>> hechosPorUrl= new ConcurrentHashMap<>();

//     public void guardarHechos(String url, List<HechoDTO> nuevosHechos) {

//         //si la url ya estaba entonces le agrego los nuevos hechos
//         hechosPorUrl.compute(url, (key, existingList) -> {
//             if (existingList == null) {
//                 return new ArrayList<>(nuevosHechos);
//             } else {
//                 //pisa si hay algun hecho con mismo titulo
//                 nuevosHechos.forEach(nuevo ->
//                         existingList.removeIf(h -> h.getTitulo().equalsIgnoreCase(nuevo.getTitulo()))
//                 );
//                 existingList.addAll(nuevosHechos);
//                 return existingList;
//             }
//         });
//     }

//     public List<HechoDTO> obtenerHechos(){
//         return hechosPorUrl.values().stream()
//                 .flatMap(List::stream)
//                 .toList();
//     }
// }
// //        return hechosPorUrl.getOrDefault(url, Collections.emptyList());
// //    }

// //    public List<HechoDTO> obtenerHechos(String url){
// //        return hechosPorUrl.getOrDefault(url, Collections.emptyList());
// //    }
// //
// //    public List<HechoDTO> obtenerTodosLosHechos() {
// //        return hechosPorUrl.values().stream()
// //                .flatMap(List::stream)
// //                .toList();
// //    }



// //
// //@Repository
// //public class FuenteDemo_Hechos {
// //
// //    private final List<Hecho> hechos = new ArrayList<>(); //me tira error si no inicializo
// //
// //    public void guardarHechos(List<Hecho> nuevosHechos) {
// //
// //        for (Hecho nuevo : nuevosHechos) {
// //            // por si ya hay uno con el mismo titulo entonces lo pisamos
// //            hechos.removeIf(h -> h.getTitulo().equalsIgnoreCase(nuevo.getTitulo()));
// //            hechos.add(nuevo);
// //        }
// //    }
// //
// //    public List<Hecho> obtenerHechos(){
// //        List<Hecho> hechosEntregados = hechos;
// //        // Limpiar la lista para que no se repitan los hechos en la siguiente consulta
// //        //hechos.clear();
// //        return hechosEntregados;
// //    }
// //}