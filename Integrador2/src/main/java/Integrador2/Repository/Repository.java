package Integrador2.Repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<Entity, ID extends Serializable> {
    public Entity findById(ID id);

    public void persist(Entity entity);

    public Entity update(Entity entity);

    public void delete(ID id);

    public List<Entity> findAll();
}
