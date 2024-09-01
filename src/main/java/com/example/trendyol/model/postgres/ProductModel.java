package com.example.trendyol.model.postgres;

import com.example.trendyol.model.abstracts.BaseEntity;
import com.example.trendyol.model.mongo.SubCategoryModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "Products")
public class ProductModel extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_name", unique = true)
    @NotBlank
    @Size(min = 3, max = 70, message = "name must be between 3 and 70 character")
    private String name;

    @Column(name = "description")
    @NotBlank
    @Size(min = 5, max = 500)
    private String description;

    @Column(name = "image_url", unique = true)
    @NotBlank
    @Size(min = 5, max = 70, message = "image url must be between 5 and 70 character")
    private String imageUrl;

    @Positive
    @Column(name = "product_price")
    @NotNull
    private double productPrice;

    @ManyToMany
    @JsonBackReference
    private List<BasketModel> basketEntityList;

    private boolean visible;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "product_subcategory",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id"))
    private List<SubCategoryModel> subCategories;


}
