package web3.web3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import web3.web3.dto.UsuariosDTO;
import web3.web3.model.UsuariosModel;
import web3.web3.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuariosModel> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public List<UsuariosDTO> getAllUsuariosAsDTO() {
        return usuariosRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Optional<UsuariosModel> getUsuarioById(Long id) {
        return usuariosRepository.findById(id);
    }

    public UsuariosModel getUsuarioByEmail(String email) {
        return usuariosRepository.findByEmail(email).orElse(null);
    }

    public UsuariosDTO getUsuarioByIdAsDTO(Long id) {
        Optional<UsuariosModel> usuario = usuariosRepository.findById(id);
        return usuario.map(this::convertToDTO).orElse(null);
    }

    public UsuariosModel createUsuario(UsuariosModel usuario) {
        // Codificar la contraseña antes de guardar
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);
        return usuariosRepository.save(usuario);
    }

    public UsuariosModel updateUsuario(Long id, UsuariosModel usuarioDetails) {
        UsuariosModel usuario = usuariosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setEmail(usuarioDetails.getEmail());

        // Codificar la contraseña si está presente
        if (usuarioDetails.getPassword() != null && !usuarioDetails.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(usuarioDetails.getPassword());
            usuario.setPassword(encodedPassword);
        }

        return usuariosRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        UsuariosModel usuario = usuariosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuariosRepository.delete(usuario);
    }

    // Método para convertir entidad a DTO
    private UsuariosDTO convertToDTO(UsuariosModel usuario) {
        UsuariosDTO dto = new UsuariosDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}