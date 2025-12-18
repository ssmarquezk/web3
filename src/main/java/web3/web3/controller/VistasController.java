package web3.web3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web3.web3.model.UsuariosModel;
import web3.web3.model.TutorialesModel;
import web3.web3.service.UsuariosService;
import web3.web3.service.TutorialesService;

@Controller
public class VistasController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private TutorialesService tutorialesService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("usuarios", usuariosService.getAllUsuariosAsDTO());
        model.addAttribute("tutoriales", tutorialesService.getAllTutorialesAsDTO());
        return "tutoriales";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        try {
            // Verificar si el email ya existe
            if (usuariosService.getUsuarioByEmail(email) != null) {
                model.addAttribute("error", "El email ya está registrado");
                return "registro";
            }

            // Crear nuevo usuario
            UsuariosModel nuevoUsuario = new UsuariosModel();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(password); // El servicio se encargará de codificarla

            usuariosService.createUsuario(nuevoUsuario);
            model.addAttribute("success", "Registro exitoso. Ahora puedes iniciar sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Error en el registro: " + e.getMessage());
            return "registro";
        }
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("usuarios", usuariosService.getAllUsuariosAsDTO());
        model.addAttribute("tutoriales", tutorialesService.getAllTutorialesAsDTO());
        return "admin";
    }

    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model) {
        model.addAttribute("usuarios", usuariosService.getAllUsuariosAsDTO());
        return "usuarios";
    }

    @GetMapping("/tutoriales")
    public String mostrarTutoriales(Model model) {
        model.addAttribute("tutoriales", tutorialesService.getAllTutorialesAsDTO());
        return "tutoriales";
    }

    @GetMapping("/tutorial-detalle/{id}")
    public String verTutorialDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("tutorial", tutorialesService.getTutorialByIdAsDTO(id));
        return "tutorial-detalle";
    }

    @GetMapping("/mis-tutoriales")
    public String misTutoriales(Model model) {
        // Obtener el nombre de usuario autenticado
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar el usuario por email
        UsuariosModel usuario = usuariosService.getUsuarioByEmail(username);

        if (usuario != null) {
            // Obtener tutoriales del usuario específico
            model.addAttribute("tutoriales", tutorialesService.getTutorialesByUsuarioAsDTO(usuario.getId()));
        } else {
            model.addAttribute("tutoriales", new java.util.ArrayList<>()); // Lista vacía si no hay usuario
        }
        return "mis-tutoriales";
    }

    @GetMapping("/crear-tutorial")
    public String mostrarFormularioCrear(Model model) {
        return "crear-tutorial";
    }

    @PostMapping("/crear-tutorial")
    public String crearTutorial(
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("urlImagen") String urlImagen,
            @RequestParam("cuerpo") String cuerpo,
            Model model) {

        // Obtener el nombre de usuario autenticado
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar el usuario por email
        UsuariosModel usuario = usuariosService.getUsuarioByEmail(username);

        if (usuario != null) {
            TutorialesModel nuevoTutorial = new TutorialesModel();
            nuevoTutorial.setTitulo(titulo);
            nuevoTutorial.setDescripcion(descripcion);
            nuevoTutorial.setUrlImagen(urlImagen);
            nuevoTutorial.setCuerpo(cuerpo);
            nuevoTutorial.setUsuario(usuario); // Asociar el tutorial al usuario

            tutorialesService.createTutorial(nuevoTutorial);
        }

        return "redirect:/mis-tutoriales";
    }

    @GetMapping("/editar-tutorial/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("tutorial", tutorialesService.getTutorialByIdAsDTO(id));
        return "editar-tutorial";
    }

    @PostMapping("/editar-tutorial/{id}")
    public String actualizarTutorial(
            @PathVariable Long id,
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("urlImagen") String urlImagen,
            @RequestParam("cuerpo") String cuerpo,
            Model model) {

        // Obtener el nombre de usuario autenticado
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar el usuario por email
        UsuariosModel usuario = usuariosService.getUsuarioByEmail(username);

        TutorialesModel tutorialExistente = tutorialesService.getTutorialById(id).orElse(null);
        if (tutorialExistente != null && usuario != null &&
            tutorialExistente.getUsuario() != null &&
            tutorialExistente.getUsuario().getId().equals(usuario.getId())) {
            tutorialExistente.setTitulo(titulo);
            tutorialExistente.setDescripcion(descripcion);
            tutorialExistente.setUrlImagen(urlImagen);
            tutorialExistente.setCuerpo(cuerpo);

            tutorialesService.updateTutorial(id, tutorialExistente);
        }

        return "redirect:/mis-tutoriales";
    }

    @PostMapping("/eliminar-tutorial/{id}")
    public String eliminarTutorial(@PathVariable Long id, Model model) {
        // Obtener el nombre de usuario autenticado
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar el usuario por email
        UsuariosModel usuario = usuariosService.getUsuarioByEmail(username);

        TutorialesModel tutorial = tutorialesService.getTutorialById(id).orElse(null);
        if (tutorial != null && usuario != null &&
            tutorial.getUsuario() != null &&
            tutorial.getUsuario().getId().equals(usuario.getId())) {
            tutorialesService.deleteTutorial(id);
        }

        return "redirect:/mis-tutoriales";
    }
}