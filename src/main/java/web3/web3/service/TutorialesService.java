package web3.web3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web3.web3.dto.TutorialesDTO;
import web3.web3.model.TutorialesModel;
import web3.web3.repository.TutorialesRepository;

@Service
public class TutorialesService {

    @Autowired
    private TutorialesRepository tutorialesRepository;

    public List<TutorialesModel> getAllTutoriales() {
        return tutorialesRepository.findAll();
    }
    
    public List<TutorialesDTO> getAllTutorialesAsDTO() {
        return tutorialesRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Optional<TutorialesModel> getTutorialById(Long id) {
        return tutorialesRepository.findById(id);
    }
    
    public TutorialesDTO getTutorialByIdAsDTO(Long id) {
        Optional<TutorialesModel> tutorial = tutorialesRepository.findById(id);
        return tutorial.map(this::convertToDTO).orElse(null);
    }

    public TutorialesModel createTutorial(TutorialesModel tutorial) {
        return tutorialesRepository.save(tutorial);
    }

    public TutorialesModel updateTutorial(Long id, TutorialesModel tutorialDetails) {
        TutorialesModel tutorial = tutorialesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial no encontrado con id: " + id));

        tutorial.setTitulo(tutorialDetails.getTitulo());
        tutorial.setDescripcion(tutorialDetails.getDescripcion());
        tutorial.setUrlImagen(tutorialDetails.getUrlImagen());
        tutorial.setCuerpo(tutorialDetails.getCuerpo());
        tutorial.setUsuario(tutorialDetails.getUsuario());

        return tutorialesRepository.save(tutorial);
    }

    public void deleteTutorial(Long id) {
        TutorialesModel tutorial = tutorialesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial no encontrado con id: " + id));
        tutorialesRepository.delete(tutorial);
    }

    public List<TutorialesModel> getTutorialesByUsuarioId(Long usuarioId) {
        return getAllTutoriales().stream()
            .filter(tutorial -> tutorial.getUsuario() != null && tutorial.getUsuario().getId().equals(usuarioId))
            .toList();
    }

    public List<TutorialesDTO> getTutorialesByUsuarioAsDTO(Long usuarioId) {
        return getTutorialesByUsuarioId(usuarioId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Método para convertir entidad a DTO
    private TutorialesDTO convertToDTO(TutorialesModel tutorial) {
        TutorialesDTO dto = new TutorialesDTO();
        dto.setId(tutorial.getId());
        dto.setTitulo(tutorial.getTitulo());
        dto.setDescripcion(tutorial.getDescripcion());
        dto.setUrlImagen(tutorial.getUrlImagen());
        dto.setCuerpo(tutorial.getCuerpo());
        
        // Agregar información del usuario si existe
        if (tutorial.getUsuario() != null) {
            dto.setUsuarioId(tutorial.getUsuario().getId());
            dto.setNombreUsuario(tutorial.getUsuario().getNombre() + " " + tutorial.getUsuario().getApellido());
        }
        
        return dto;
    }
}