package com.example.imagedata.repository;

import com.example.imagedata.model.Image;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String name);

    Optional<Image> findFirstByNameLike(String name, Sort sort);

}
