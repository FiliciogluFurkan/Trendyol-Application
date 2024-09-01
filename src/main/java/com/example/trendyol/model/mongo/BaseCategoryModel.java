package com.example.trendyol.model.mongo;


import com.example.trendyol.model.abstracts.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "baseCategories")
public class BaseCategoryModel extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 40, message = "Base category name's length must be between 3 and 40 character")
    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "baseCategory")
    private List<SubCategoryModel> subCategoryModelList;

    private boolean visible;

}
