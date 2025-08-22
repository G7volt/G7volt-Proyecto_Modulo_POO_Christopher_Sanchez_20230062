package Controller;

import Models.DTO.Libros.LibrosDTO;
import Service.LibrosService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/apiLibros")
public class LibroController {

    private LibrosService service;

    @GetMapping("/getLibros")
    private ResponseEntity<?> getLibros(){
        try{
            List<LibrosDTO> libros = service.getAllLibros();
            if (libros.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("status", "No hay Libros registrados"));
            }
            return ResponseEntity.ok(libros);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "error al obtener estudiantes",
                            "message", e.getMessage()));
        }
    }

    @GetMapping("/getLibros/{id}")
    private ResponseEntity<LibrosDTO> getLibroID(@PathVariable Long id) {
        try{
            LibrosDTO cliente = service.getLibroPorId(id);

            return ResponseEntity.ok(cliente);
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Map<String, Object>> insertLibros(@Valid @RequestBody LibrosDTO json, HttpServletRequest request){
        try{
            LibrosDTO response = service.insertLibros(json);
            if (response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Insercion incorrecta",
                        "Estatus", "Insercion Incorrecta",
                        "Descripcion", "Verifique los datos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "Estado", "Completado",
                            "data", response
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "Status", "Error",
                            "Message", "Error al registrar",
                            "Details", e.getMessage()
                    ));
        }
    }
}
