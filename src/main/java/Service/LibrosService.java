package Service;

import Entity.LibroEntity;
import Models.DTO.Libros.LibrosDTO;
import Repository.LibrosRepository;
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




}
