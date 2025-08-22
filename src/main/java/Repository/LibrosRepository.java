package Repository;

import Entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository<LibroEntity, Long> {
}
