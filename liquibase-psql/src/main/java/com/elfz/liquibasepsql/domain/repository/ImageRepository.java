package com.elfz.liquibasepsql.domain.repository;


import com.elfz.liquibasepsql.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}