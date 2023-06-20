package com.agussuhardi.springboot3integrationtest.repository;

import com.agussuhardi.springboot3integrationtest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @author agus.suhardii@gmail.com
 * @created 20/06/23/06/2023 :20.13
 * @project spring-boot-3-integration-test
 */
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

}