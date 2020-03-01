package com.example.contact.dao;

import com.example.contact.entity.File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<File, Integer> {
    List<File> findById(int id);
    void deleteById(int id);
}
