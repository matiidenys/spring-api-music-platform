package edu.study.two.springlab.service.interfaces;

import java.util.List;

public interface IService<T> {
    T create(T t);
    T get(String id);
    T update(T t);
    void delete(String id);
    List<T> getAll();
}
