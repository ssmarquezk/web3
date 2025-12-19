package web3.web3.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import web3.web3.model.TutorialesModel;
import web3.web3.model.UsuariosModel;
import web3.web3.repository.TutorialesRepository;
import web3.web3.repository.UsuariosRepository;

import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TutorialesRepository tutorialesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        // Inicializar usuarios si no existen
        if (usuariosRepository.count() == 0) {
            crearUsuarios();
        }

        // Inicializar tutoriales si no existen
        if (tutorialesRepository.count() == 0) {
            List<UsuariosModel> usuarios = usuariosRepository.findAll();
            if (!usuarios.isEmpty()) {
                crearTutoriales(usuarios);
            } else {
                System.out.println("No hay usuarios disponibles para asociar con los tutoriales.");
            }
        }
    }

    private void crearUsuarios() {
        // Usuario admin
        UsuariosModel admin = new UsuariosModel();
        admin.setNombre("Admin");
        admin.setApellido("Principal");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        usuariosRepository.save(admin);

        // Usuarios normales
        String[] nombres = {
                "Carlos",
                "Ana",
                "Miguel",
                "Laura",
                "David",
                "Sofía",
                "Andrés",
                "Lucía",
                "Javier",
                "Elena",
                "Roberto",
                "María",
                "Fernando",
                "Claudia",
                "José",
                "Patricia",
                "Luis",
                "Verónica",
                "Raúl",
                "Carolina",
                "Tomás",
                "Gabriela",
                "Emilio",
                "Isabella",
                "Ricardo"
        };
        String[] apellidos = {
                "García",
                "Martínez",
                "Rodríguez",
                "López",
                "Hernández",
                "González",
                "Pérez",
                "Sánchez",
                "Ramírez",
                "Torres",
                "Flores",
                "Castillo",
                "Romero",
                "Álvarez",
                "Mendoza",
                "Juárez",
                "Domínguez",
                "Vázquez",
                "Cruz",
                "Ortiz",
                "Gómez",
                "Morales",
                "Silva",
                "Delgado",
                "Rojas"
        };

        for (int i = 0; i < 24; i++) {
            UsuariosModel usuario = new UsuariosModel();
            usuario.setNombre(nombres[i]);
            usuario.setApellido(apellidos[i]);
            usuario.setEmail("usuario" + (i + 1) + "@example.com");
            usuario.setPassword(passwordEncoder.encode("password" + (i + 1)));
            usuariosRepository.save(usuario);
        }

        System.out.println("25 usuarios iniciales insertados correctamente (1 admin, 24 usuarios).");
    }

    private void crearTutoriales(List<UsuariosModel> usuarios) {
        // Arrays con datos para generar tutoriales
        String[] titulos = {
                "Programación de NPCs con IA absurda en Unity",
                "Cómo hacer que tu personaje se mueva como pato en Godot",
                "Modelado 3D de hongos que cantan en Blender",
                "Diseño de niveles imposibles en Unreal Engine",
                "Creación de sonidos con el alma en Reaper",
                "Animación de árboles que lloran en Blender",
                "Sistema de combate con espaguetis en Unity",
                "Guion de videojuego para vegetarianos enojados",
                "Texturizado de armas con patrones de gatitos en Substance Painter",
                "Construcción de mundos con física bizarra en Godot",
                "Diseño de personajes con solo cejas en Blender",
                "Sistema de crafting con emociones en Unity",
                "Música para jefes que odian la música en Reaper",
                "Creación de UI con emojis en Godot",
                "Animación de animales que filosofan en Blender",
                "Diseño de puzzles con lógica de gallinas en Unreal",
                "Modelado de armaduras con frutas en Blender",
                "Sistema de daño con emociones en Unity",
                "Guion de juego para perros con ansiedad",
                "Texturizado con patrones de calcetines en Substance Painter",
                "Niveles de terror con objetos del baño en Godot",
                "Creación de sonidos con vegetales en Reaper",
                "Mundo de fantasía con vegetales en Unreal Engine",
                "Programación de enemigos con personalidad bipolar en Unity",
                "Modelado de arquitectura imposible en Blender",
                "Sistema de comercio con extraterrestres en Godot",
                "Animación de emociones en objetos inanimados en Blender",
                "Diseño de misiones para vegetarianos sensibles en Unreal",
                "Sistema de diálogo con plantas en Unity",
                "Guion de aventura para calcetines perdidos",
                "Texturizado con patrones de salchichas en Substance Painter",
                "Creación de sonidos con emociones en Reaper",
                "Diseño de mundo de sueños en Godot",
                "Programación de NPCs que hablan en acrósticos en Unity",
                "Modelado de criaturas con solo codos en Blender",
                "Sistema de economía con emociones en Unreal",
                "Animación de comida que canta en Blender",
                "Constructor de mundos con gravedad invertida en Godot",
                "Sistema de habilidades con sabores en Unity",
                "Guion de juego para burbujas pensantes",
                "Texturizado con patrones de nieve en Substance Painter",
                "Creación de música con objetos del hogar en Reaper",
                "Diseño de niveles con lógica cuántica en Unreal",
                "Programación de enemigos con fobias en Unity",
                "Modelado de objetos que tienen sentimientos en Blender",
                "Sistema de comercio con animales extraterrestres en Godot",
                "Animación de emociones en construcciones en Blender",
                "Constructor de historias con personajes que no existen en Unreal",
                "Sistema de magia con ingredientes de cocina en Unity",
                "Guion de juego para sombras con personalidad"
        };

        String[] descripciones = {
                "Aprende a crear NPCs con comportamientos completamente ilógicos",
                "Descubre cómo darle movimientos dignos de baile a tus personajes",
                "Crea objetos tridimensionales que desafían la lógica",
                "Construye mundos que no siguen las leyes de la física",
                "Produce sonidos inolvidables con herramientas inesperadas",
                "Da vida a objetos que no deberían tenerla",
                "Implementa mecánicas de juego con ingredientes culinarios",
                "Crea historias que ni tú mismo entenderás",
                "Texturiza objetos con diseños que no tienen sentido",
                "Construye realidades alternativas con herramientas simples",
                "Modela personajes con características inusuales",
                "Crea sistemas con lógica absurda",
                "Componga melodías para criaturas que odian la música",
                "Diseña interfaces con emojis únicamente",
                "Anima seres con emociones complejas",
                "Construye acertijos con lógica de gallina",
                "Crea armaduras con alimentos",
                "Sistema de daño emocional para tu juego",
                "Escribe guiones para criaturas sensibles",
                "Texturizado con diseños de ropa interior",
                "Niveles que te harán cuestionar la realidad",
                "Sonidos con vegetales como instrumentos",
                "Un mundo donde solo existen alimentos",
                "Enemigos con trastornos de personalidad",
                "Arquitectura que desafía la lógica",
                "Comercio con seres de otros planetas",
                "Objetos con emociones humanas",
                "Puzzles para vegetarianos sensibles",
                "Diálogos con seres inanimados",
                "Aventuras para calcetines abandonados",
                "Texturas con diseños de salchichas",
                "Sonidos con emociones intensas",
                "Un mundo de sueños sin fin",
                "NPCs que solo hablan en versos",
                "Criaturas con solo codos como características",
                "Economía basada en emociones",
                "Comida que canta canciones tristes",
                "Gravedad que funciona al revés",
                "Habilidades que saben a algo",
                "Burbujas con pensamientos profundos",
                "Patrones que parecen nieve eterna",
                "Música con objetos del hogar",
                "Niveles con física cuántica imposible",
                "Enemigos con miedos absurdos",
                "Objetos con emociones intensas",
                "Comercio con animales alienígenas",
                "Construcciones con sentimientos",
                "Historias con personajes que no existen",
                "Magia con ingredientes cotidianos",
                "Guion para sombras pensantes"
        };

        String[] urlsImagenes = {
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRX8vH1mVVatSIEwsfCHL6twtoPTHCI5wg7ig&s",
                "https://i.ytimg.com/vi/fIHQ1fqxA_M/maxresdefault.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_MJ5dgcKh7j2K7b1TwUup6UEdmB9l7vVu2g&s",
                "https://kreonit.com/wp-content/uploads/2021/03/level-design5-1.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnPYi1FiJlq7U_9ecORqCKGj3nvfF3YUV7Qw&s",
                "https://i.ytimg.com/vi/y7PdiGXbrD0/maxresdefault.jpg",
                "https://i.ytimg.com/vi/uGFzWM1sJjU/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBLKt5dfOsg8y2kJ8ApjFZgE_zu4g",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ10zbDET09DdPM0itOc3ak3iYy21KF_kVhiA&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnUoqtUHFr_tOyKis4VX8xW4n8I1xDNZxLTA&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6z2wcCRfpAY8V94eCEbhWa4AZOEFdIFXGHA&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe6zQI6qf1Jm3gLIhd3F3aC0IPcHsICLuJcA&s",
                "https://i.ytimg.com/vi/LmQ6U3YkHHk/maxresdefault.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsR8u6ESLB74DqvOhxoTN-LgB02V6dLPve6g&s",
                "https://i.ytimg.com/vi/ALhInfxq4KE/maxresdefault.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSK56Q1hx44u-ugrIpVFSwfuC8E8dYuQMQaCg&s",
                "https://media.fab.com/image_previews/gallery_images/ff6d69a1-3fe0-49c6-9619-e415a343d654/c2d5fe4c.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBrzM5PlokeVBnAbUqou3jsfc59q2QB4Veaw&s",
                "https://i.ytimg.com/vi/NHuoiGtiZ7M/maxresdefault.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsXvrAjd0L6Xvl0EtINWgLHQaCkKNOoYNF4Q&s",
                "https://i.ytimg.com/vi/cHp8PLa0q18/maxresdefault.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9RcSZ0BbGuHjxmgEv5qH4qolcQoXrQ2APvw&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgQiut_ORowYAdYy1mm773LlQqacJK3A5gVw&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbMIt567al7mNnOsr68dVmbs0WlueptpVQvw&s",
                "https://i.ytimg.com/vi/nkYJjGyaGa4/maxresdefault.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-uZhwrUpYYsEI_mY9ZGaHly6i34EQKPS05g&s"


        };

        String[] cuerpos = {
                "Este tutorial detalla cómo implementar inteligencia artificial para no-jugadores (NPCs) en Unity. Aprenderás a crear enemigos con comportamientos realistas que responden de manera lógica a las acciones del jugador. Comenzaremos implementando un sistema de patrullaje con waypoints que permiten a los enemigos moverse por el escenario de forma natural. Luego, añadiremos lógica de detección para que los NPCs reconozcan al jugador en su área de visión. Veremos cómo implementar estados de alerta y combate que cambian el comportamiento del enemigo. El sistema incluye toma de decisiones basada en variables como la distancia al jugador, la salud del enemigo y su tipo de arma. Utilizaremos máquinas de estado para controlar las transiciones entre diferentes comportamientos. Se abordarán técnicas de optimización para mantener buen rendimiento incluso con muchos NPCs activos. Este tutorial es fundamental para cualquier desarrollador que quiera crear juegos con enemigos inteligentes y desafiantes.",
                "Aprende a implementar un sistema de movimiento fluido y realista para tu personaje en Godot. Este tutorial cubre desde los conceptos básicos de la física de movimiento hasta técnicas avanzadas de animación. Comenzaremos con controles simples de movimiento 2D y 3D, incluyendo desplazamiento con aceleración y desaceleración. Luego veremos cómo sincronizar el movimiento con animaciones de caminar, correr y detenerse. Implementaremos sistemas de movimiento específicos para diferentes tipos de juego: plataformas, top-down, y vistas laterales. Aprenderás a usar herramientas como RayCast para detectar colisiones y Jump para permitir saltos controlados. Se cubrirá la gestión de entradas para mantener el movimiento responsive. El tutorial incluye ejemplos prácticos con código comentado para facilitar la comprensión. Al finalizar, tendrás un sistema de movimiento profesional que mejora la experiencia del jugador.",
                "En este tutorial aprenderás a crear modelos 3D profesionales para videojuegos usando Blender. Comenzaremos con los fundamentos de la interfaz y herramientas básicas de modelado. Luego, crearemos un personaje sencillo paso a paso, cubriendo técnicas como extrusiones, cortes y subdivisiones. Veremos cómo crear topology limpio para animación y optimización. Aprenderás a usar modifiers como Mirror y Subdivision Surface para agilizar el proceso. El tutorial cubre técnicas para crear formas orgánicas y duras. Se explicarán conceptos como UV mapping para aplicar texturas correctamente. Se mostrarán consejos para mantener una topología eficiente que se exporte correctamente a motores como Unity o Unreal. Al finalizar, tendrás un modelo optimizado para videojuegos listo para texturizar y animar.",
                "Aprende a diseñar niveles cautivadores en Unreal Engine con este tutorial completo sobre diseño de niveles. Comenzaremos explorando el editor de niveles y las herramientas de construcción rápida. Veremos principios de diseño como flujo de jugador, guía visual y balance de desafíos. Implementaremos sistemas de iluminación dinámica y estática para crear ambientes inmersivos. Aprenderás a usar Blueprints para crear mecánicas específicas del nivel sin necesidad de código. Se cubrirán técnicas para crear entornos abiertos y lineales efectivos. El tutorial incluye ejemplos de cómo guiar al jugador sin que se sienta forzado. Veremos cómo crear puntos de interes y recompensas que motiven la exploración. Se abordarán técnicas de optimización para mantener buen rendimiento en escenarios complejos. Este conocimiento es esencial para crear experiencias memorables.",
                "Este tutorial te enseña a crear y manipular audio de calidad para videojuegos usando Reaper. Comenzaremos con los fundamentos de la interfaz y la gestión de proyectos de audio. Luego, veremos cómo importar y editar diferentes tipos de sonido: efectos, música y diálogos. Aprenderás a usar ecualización, compresión y otros efectos para mejorar la calidad del audio. Se abordará la creación de ambientes sonoros y cómo sincronizarlos con eventos del juego. Veremos técnicas para crear loops de música sin clics audibles. El tutorial cubre la importancia de los niveles de volumen y la mezcla equilibrada. Se mostrarán técnicas para crear una mezcla que funcione bien en diferentes sistemas de sonido. Se explicarán formatos de exportación adecuados para motores de juego. Al finalizar, podrás crear audio profesional para tus proyectos.",
                "Aprende a animar objetos y personajes en Blender con técnicas profesionales de animación. Este tutorial comienza con los fundamentos de la línea de tiempo y los keyframes. Veremos cómo crear animaciones básicas de objetos y cámaras. Luego pasaremos a la animación de personajes con armazones (rigging) sencillos. Aprenderás a usar la curva de interpolación para crear movimientos más realistas. Se cubrirán los principios de animación como el squash & stretch, el follow through y la overlaping action. Veremos cómo usar constraints para automatizar ciertos movimientos. El tutorial incluye técnicas para animación de caminar, correr y otras acciones comunes. Se abordará la importancia de la anticipación y el timing. Al finalizar, podrás crear animaciones fluidas que aporten vida y personalidad a tus modelos.",
                "En este tutorial aprenderás a implementar sistemas de combate efectivos en Unity. Comenzaremos con un sistema de combate básico cuerpo a cuerpo y progresaremos a sistemas más complejos. Veremos cómo detectar colisiones de ataque y calcular daño de manera realista. Implementaremos mecánicas como combos, bloqueos y esquivas. Aprenderás a usar triggers para detectar ataques y animator para sincronizar animaciones con eventos. El tutorial cubre sistemas de salud, armas y habilidades especiales. Se mostrarán técnicas para hacer que el combate se sienta satisfactorio y con feedback claro. Veremos cómo implementar sistemas de balance para diferentes armas y enemigos. Se abordarán técnicas de optimización para mantener buen rendimiento en combates intensos. Este conocimiento es esencial para muchos géneros de videojuegos.",
                "Aprende a escribir guiones efectivos para videojuegos con este tutorial práctico. Comenzaremos explorando las diferencias entre guion para cine y para videojuegos, donde el jugador tiene control. Veremos cómo crear personajes memorables y diálogos convincentes. El tutorial cubre la estructura de historias interactivas y cómo mantener la coherencia con las decisiones del jugador. Aprenderás a integrar la narrativa con la jugabilidad sin interrumpir la experiencia. Se abordarán técnicas para presentar información al jugador de manera natural. Veremos cómo crear ramificaciones de historia basadas en las elecciones del jugador. Se cubrirán sistemas de narrativa emergente que permiten historias únicas en cada partida. Se explicará cómo la narrativa puede reforzar las mecánicas del juego. Este conocimiento es crucial para crear experiencias inmersivas.",
                "En este tutorial aprenderás a crear texturas de alta calidad para tus modelos 3D usando Substance Painter. Comenzaremos con la interfaz y herramientas principales del software. Veremos cómo aplicar materiales base y crear patrones realistas. Aprenderás a usar máscaras para aplicar texturas selectivamente. El tutorial cubre técnicas para crear desgaste realista, como suciedad, óxido y rayones. Se abordarán conceptos como mapas de normal, roughness y metallic. Veremos cómo usar Smart Materials para agilizar el proceso. Se cubrirán técnicas para mantener consistencia visual en todo el proyecto. El tutorial incluye consejos para optimizar texturas para videojuegos. Se explicarán diferentes técnicas para diferentes tipos de superficies. Al finalizar, crearás texturas profesionales que mejoren la apariencia de tus modelos.",
                "Aprende a construir mundos inmersivos en Godot con este tutorial sobre creación de entornos. Comenzaremos con la organización de escenas y la creación de sistemas de juego escalables. Veremos cómo implementar sistemas de físicas realistas para diferentes materiales y ambientes. El tutorial cubre la creación de sistemas de interacción con el entorno. Aprenderás a usar áreas para detectar colisiones y eventos. Se abordarán técnicas para crear entornos dinámicos y reactivos. Veremos cómo implementar sistemas de clima y tiempo del día. Se cubrirán técnicas para mantener buenas tasas de fotogramas en entornos complejos. El tutorial incluye ejemplos prácticos de cómo el entorno puede ser parte de la jugabilidad. Al finalizar, crearás mundos que se sientan vivos y respondan al jugador."
        };

        // Crear 50 tutoriales
        for (int i = 0; i < 50; i++) {
            TutorialesModel tutorial = new TutorialesModel();
            tutorial.setTitulo(titulos[i % titulos.length] + " #" + (i + 1));
            tutorial.setDescripcion(descripciones[i % descripciones.length]);
            tutorial.setUrlImagen(urlsImagenes[random.nextInt(urlsImagenes.length)]);
            tutorial.setCuerpo(cuerpos[random.nextInt(cuerpos.length)]);

            // Asociar a usuarios aleatoriamente, pero asegurar que el admin tenga algunos
            if (i < 5) {
                tutorial.setUsuario(usuarios.get(0)); // Asociar algunos al admin
            } else {
                tutorial.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            }

            tutorialesRepository.save(tutorial);
        }

        System.out.println("50 tutoriales iniciales insertados correctamente.");
    }
}

