package shared.transferobject.mapper;

import java.util.List;

public interface Mapper<K, T> {
    T entityToDTO(K entity);
    K DTOToEntity(T dto);
    List<T> allEntitiesToDTOs(List<K> entities);
    List<K> allDTOsToEntities(List<T> dtos);
}
