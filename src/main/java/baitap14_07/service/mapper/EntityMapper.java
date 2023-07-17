package baitap14_07.service.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entỉy);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
