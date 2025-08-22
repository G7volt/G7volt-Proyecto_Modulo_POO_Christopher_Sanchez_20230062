package Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Table(name = "TBLIBROS")
@Entity
@ToString
@EqualsAndHashCode
public class LibroEntity {

    @Id
    @Column(name = "IDLIBRO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIBROS")
    @SequenceGenerator(name = "SEQ_LIBROS", sequenceName = "SEQ_LIBROS", allocationSize = 1)
    private int idLibros;

    @NotNull
    @Column(name = "TITULO")
    private String titulo;

    @NotNull
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "ANIO_PUBLICACION")
    @Null
    private int anio_publicacion;

    @Column(name = "GENERO")
    @Null
    private String generoLibro;

    @Column(name = "IDAUTOR")
    private int idAutor;

}
