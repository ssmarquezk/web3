package web3.web3.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({ "id", "nombre", "apellido", "email" })
@Data
public class UsuariosDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}