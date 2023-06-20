package com.agussuhardi.springboot3integrationtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author agus.suhardii@gmail.com
 * &#064;created  20/06/23/06/2023 :20.13
 * {@code @project} spring-boot-3-integration-test
 */
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @NotNull(message = "id can not null")
    private String id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "name can not null")
    private String name;

    @Column(name = "qty", nullable = false)
    @NotNull(message = "qty can not null")
    private int qty;

}
