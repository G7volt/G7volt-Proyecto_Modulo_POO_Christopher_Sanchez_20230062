package Controller;

import Models.DTO.Libros.LibrosDTO;
import Service.LibrosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiLibros")
public class LibroController {

    private LibrosService service;

    @GetMapping("/getLibros")
    private ResponseEntity<?> getLibros(){
        List<LibrosDTO> libros = service.getAllLibros();
        if (libros.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "No hay Libros registrados"));
        }
    }
}
