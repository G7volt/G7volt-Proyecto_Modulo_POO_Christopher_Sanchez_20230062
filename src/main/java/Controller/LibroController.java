package Controller;

import Models.DTO.Libros.LibrosDTO;
import Service.LibrosService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
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
                    .body(Map.of("error", "error al obtener Libros",
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

    @PutMapping("/updateLibro/{id}")
    public ResponseEntity<?> modificarLibroDTO(@PathVariable Long id, @Valid @RequestBody LibrosDTO libros, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();

            bindingResult.getFieldErrors().forEach(errors -> errores.put(errors.getField(), errors.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            LibrosDTO libroActualizado = service.update(id, libros);
            return ResponseEntity.ok(libroActualizado);
        }
        catch (ExceptionNoEncontrado e){
            return ResponseEntity.notFound().build();
        }

        catch (ExceptionColumnDuplicated e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("Error", "Datos Duplicados", "campo", e.getColumnDuplicated())
            );
        }
    }

    @DeleteMapping("/deleteLibro/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable Long id){
        try{
            if (!service.delete(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .header("X-Mensaje-Error", "Libro no encontrado")
                        .body(Map.of(
                             "Error", "Not Found",
                             "Mensaje", "El Libro no se ha econtrado",
                             "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso Completado",
                    "Message", "El Libro ha sido eliminado"
            ));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status", "Error",
                    "Message", "Error al Eliminar el libro",
                    "Detail", e.getMessage()
            ));
        }
    }


}
