package web3.web3.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

// link a consola http://localhost:8080/h2-console

@Entity
@Table(name = "tutoriales")
@Data
@JsonPropertyOrder({ "id", "titulo", "descripcion", "urlImagen", "cuerpo", "usuario" })
public class TutorialesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(length = 500)
    private String urlImagen;

    @Lob
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuariosModel usuario;

}
