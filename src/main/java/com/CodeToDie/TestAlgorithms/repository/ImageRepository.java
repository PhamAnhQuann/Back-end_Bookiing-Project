package com.CodeToDie.TestAlgorithms.repository;

import com.CodeToDie.TestAlgorithms.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageProduct,Long> {

}
