package com.example.trendyol.model.elasticsearch;


import com.example.trendyol.model.mongo.SubCategoryModel;
import com.example.trendyol.model.postgres.BasketModel;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@Data
@SuperBuilder
@Document(indexName = "products")
public class ProductElasticSearchModel {

    @Id
    private Long id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "image_url", type = FieldType.Text)
    private String imageUrl;

    @Field(name = "visible", type = FieldType.Boolean)
    private boolean visible;

    @Field(name = "description", type = FieldType.Text)
    private String description;

    @Field(name = "product_price", type = FieldType.Double)
    private double productPrice;

  /*  @Field(name = "basket_entity_list", type = FieldType.Object)
    private List<BasketModel> basketEntityList;

    @Field(name = "sub_categories", type = FieldType.Object)
    private List<SubCategoryModel> subCategories;*/
}
