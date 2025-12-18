package web3.web3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web3.web3.dto.UsuariosDTO;
import web3.web3.model.UsuariosModel;
import web3.web3.service.UsuariosService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosRestController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> getAllUsuarios() {
        List<UsuariosDTO> usuarios = usuariosService.getAllUsuariosAsDTO();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> getUsuarioById(@PathVariable Long id) {
        UsuariosDTO usuario = usuariosService.getUsuarioByIdAsDTO(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UsuariosDTO> createUsuario(@RequestBody UsuariosModel usuario) {
        UsuariosModel createdUsuario = usuariosService.createUsuario(usuario);
        UsuariosDTO dto = convertToDTO(createdUsuario);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuariosModel usuarioDetails) {
        try {
            UsuariosModel updatedUsuario = usuariosService.updateUsuario(id, usuarioDetails);
            UsuariosDTO dto = convertToDTO(updatedUsuario);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try {
            usuariosService.deleteUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Método de conversión auxiliar
    private UsuariosDTO convertToDTO(UsuariosModel usuario) {
        UsuariosDTO dto = new UsuariosDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}