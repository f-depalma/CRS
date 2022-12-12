package shared.transferobject.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<E, D> {
    public abstract D entityToDTO(E entity);
    public abstract E DTOToEntity(D dto);

    public List<D> allEntitiesToDTOs(List<E> entities) {
        List<D> dtos = new ArrayList<>();

        for (E entity : entities) {
            dtos.add(entityToDTO(entity));
        }

        return dtos;
    }

    public List<E> allDTOsToEntities(List<D> dtos) {
        List<E> entities = new ArrayList<>();

        for (D dto : dtos) {
            entities.add(DTOToEntity(dto));
        }

        return entities;
    }
}
