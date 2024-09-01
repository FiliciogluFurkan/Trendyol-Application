package com.example.trendyol.model.mongo;

import com.example.trendyol.model.postgres.ProductModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subCategories")
public class SubCategoryModel extends BaseMongoEntity{


    @Column(unique = true)
    private String name;

    @Column(name = "visible")
    private boolean visible;


    @ManyToOne
    @JoinColumn(name = "base_category_id")
    private BaseCategoryModel baseCategory;

    @ManyToMany(mappedBy = "subCategories")
    @JsonManagedReference
    private List<ProductModel> products;

}
