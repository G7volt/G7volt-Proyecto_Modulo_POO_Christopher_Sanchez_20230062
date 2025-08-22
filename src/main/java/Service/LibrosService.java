package Service;

import Entity.LibroEntity;
import Models.DTO.Libros.LibrosDTO;
import Repository.LibrosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LibrosService {

    private LibrosRepository repository;

    public List<LibrosDTO> getAllLibros(){
        List<LibroEntity> libros = repository.findAll();
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public LibrosDTO getLibroPorId(Long id){
        LibroEntity libros = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID de: " + id));
        return convertirADTO(libros);
    }


    public LibrosDTO insertLibros(@Valid LibrosDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El Libro no puede estar en blanco");
        }
        try {
            LibroEntity libroEntity = convertirAEntity(jsonData);
            LibroEntity estudianteSave = repository.save(libroEntity);
            return convertirADTO(estudianteSave);
        }catch (Exception e){
            log.error("Error al registrar al libro: " + e.getMessage());
            throw new ExceptionNoEncontrado("Error al registrar el libro: " + e.getMessage());
        }
    }



    public LibrosDTO update(Long id, @Valid LibrosDTO jsonDTO){
        if (jsonDTO == null){
            throw new IllegalArgumentException("El no puede estar en blanco");
        }
        LibroEntity libroData = repository.findById(id).orElseThrow(() -> new Exception.ExceptionNoEncontrado("Libro no encontrado"));

    }

    private LibrosDTO convertirADTO(LibroEntity libro){
        LibrosDTO dto = new LibrosDTO();
        dto.setIdLibros(libro.getIdLibros());
        dto.setTitulo(libro.getTitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setAnio_publicacion(libro.getAnio_publicacion());
        dto.setGeneroLibro(libro.getGeneroLibro());
        dto.setIdAutor(libro.getIdAutor());
        return dto;
    }

    private LibroEntity convertirAEntity(@Valid LibrosDTO json){
        LibroEntity objEntity = new LibroEntity();
        objEntity.setIdLibros(json.getIdLibros());
        objEntity.setTitulo(json.getTitulo());
        objEntity.setIsbn(json.getIsbn());
        objEntity.setAnio_publicacion(json.getAnio_publicacion());
        objEntity.setGeneroLibro(json.getGeneroLibro());
        objEntity.setIdAutor(json.getIdAutor());
        return objEntity;
    }






}
