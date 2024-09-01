package com.example.trendyol.repository;

import com.example.trendyol.model.elasticsearch.ProductElasticSearchModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchProductRepository extends ElasticsearchRepository<ProductElasticSearchModel, Long> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    Iterable<ProductElasticSearchModel> findByName(String searchTerm);


}
