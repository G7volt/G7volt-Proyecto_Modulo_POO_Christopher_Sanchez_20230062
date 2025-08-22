package Models.DTO.Libros;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibrosDTO {

    private int idLibros;

    @NotNull(message = "El titulo no puede estar vacio")
    private String titulo;

    @NotNull(message = "El ISBN no puede estar vacio")
    private String isbn;

    @Null
    private int anio_publicacion;

    @Null
    private String generoLibro;

    private int idAutor;


}
