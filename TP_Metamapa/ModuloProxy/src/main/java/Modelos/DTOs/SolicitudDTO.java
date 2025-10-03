package Modelos.DTOs;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.config.ConfigDataLocationResolverContext;

import java.util.UUID;

@Getter
@Setter
public class SolicitudDTO
{
    private String motivo;
    private Long idHecho;

    public SolicitudDTO(){}
}
