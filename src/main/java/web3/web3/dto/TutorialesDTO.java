package web3.web3.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({ "id", "titulo", "descripcion", "urlImagen", "cuerpo", "usuarioId", "nombreUsuario" })
@Data
public class TutorialesDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String urlImagen;
    private String cuerpo;
    private Long usuarioId;
    private String nombreUsuario;
}