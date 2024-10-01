package JPARepository;

import java.io.Serializable;
import java.util.List;

public interface Repository<Entity, ID extends Serializable>{
    Entity findById(ID id);
    Entity persist(Entity e);
    Entity delete(ID id);
    Entity update(Entity e);
    List<Entity> findAll();
}
