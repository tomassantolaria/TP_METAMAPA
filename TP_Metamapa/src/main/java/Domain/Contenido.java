package Domain;

public class Contenido {
    private String texto;
    private ContenidoMultimedia contenido_multimedia;

    public Contenido(String texto, ContenidoMultimedia contenido_multimedia){
        this.texto = texto;
        this.contenido_multimedia = contenido_multimedia;
    }

    public boolean tieneContenidoMultimedia() {
        return contenido_multimedia != null;
    }

    public String getTexto() {
        return texto;
    }

    public ContenidoMultimedia getContenidoMultimedia() {
        return contenido_multimedia;
    }
}
