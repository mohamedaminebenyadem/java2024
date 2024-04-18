package services;

import java.util.List;

public interface CrudInterface <T>{
    public void create(T entity);
    public void update(T entity);
    public void delete(int id);
    public T getById(int id);
    public List<T> getAll();
}
