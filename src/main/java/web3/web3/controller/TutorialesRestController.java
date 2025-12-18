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

import web3.web3.dto.TutorialesDTO;
import web3.web3.model.TutorialesModel;
import web3.web3.service.TutorialesService;

@RestController
@RequestMapping("/api/tutoriales")
public class TutorialesRestController {

    @Autowired
    private TutorialesService tutorialesService;

    @GetMapping
    public ResponseEntity<List<TutorialesDTO>> getAllTutoriales() {
        List<TutorialesDTO> tutoriales = tutorialesService.getAllTutorialesAsDTO();
        return new ResponseEntity<>(tutoriales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorialesDTO> getTutorialById(@PathVariable Long id) {
        TutorialesDTO tutorial = tutorialesService.getTutorialByIdAsDTO(id);
        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TutorialesDTO> createTutorial(@RequestBody TutorialesModel tutorial) {
        TutorialesModel createdTutorial = tutorialesService.createTutorial(tutorial);
        TutorialesDTO dto = convertToDTO(createdTutorial);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorialesDTO> updateTutorial(@PathVariable Long id, @RequestBody TutorialesModel tutorialDetails) {
        try {
            TutorialesModel updatedTutorial = tutorialesService.updateTutorial(id, tutorialDetails);
            TutorialesDTO dto = convertToDTO(updatedTutorial);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable Long id) {
        try {
            tutorialesService.deleteTutorial(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TutorialesDTO>> getTutorialesByUsuarioId(@PathVariable Long usuarioId) {
        List<TutorialesModel> tutoriales = tutorialesService.getTutorialesByUsuarioId(usuarioId);
        List<TutorialesDTO> dtos = tutoriales.stream()
            .map(this::convertToDTO)
            .collect(java.util.stream.Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    // Método de conversión auxiliar
    private TutorialesDTO convertToDTO(TutorialesModel tutorial) {
        TutorialesDTO dto = new TutorialesDTO();
        dto.setId(tutorial.getId());
        dto.setTitulo(tutorial.getTitulo());
        dto.setDescripcion(tutorial.getDescripcion());
        dto.setUrlImagen(tutorial.getUrlImagen());
        dto.setCuerpo(tutorial.getCuerpo());
        
        if (tutorial.getUsuario() != null) {
            dto.setUsuarioId(tutorial.getUsuario().getId());
            dto.setNombreUsuario(tutorial.getUsuario().getNombre() + " " + tutorial.getUsuario().getApellido());
        }
        
        return dto;
    }
}