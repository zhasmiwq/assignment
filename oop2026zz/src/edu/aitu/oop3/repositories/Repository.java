package edu.aitu.oop3.repositories;
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    ID save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();

    default boolean existsById(ID id) {
        return findById(id).isPresent();
    }
}

