package shared.transferobject.mapper;

import java.util.List;

public interface Mapper<E, D> {
    D entityToDTO(E entity);
    E DTOToEntity(D dto);
    List<D> allEntitiesToDTOs(List<E> entities);
    List<E> allDTOsToEntities(List<D> dtos);
}
