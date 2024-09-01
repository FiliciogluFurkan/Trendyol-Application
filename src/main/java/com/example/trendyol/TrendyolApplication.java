package com.example.trendyol;

import com.example.trendyol.model.elasticsearch.ProductElasticSearchModel;
import com.example.trendyol.repository.SearchProductRepository;
import com.example.trendyol.service.kafka.EmailProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TrendyolApplication implements CommandLineRunner {

    @Autowired
    private  SearchProductRepository searchProductRepository;

    @Autowired
    private EmailProducerService service;

    public static void main(String[] args) {
        SpringApplication.run(TrendyolApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dummySource();
    }



    public void dummySource() {

        ProductElasticSearchModel productElasticSearchModel1 = ProductElasticSearchModel.builder()
                .id(1L)
                .name("nike ayakkabi")
                .visible(true)
                .imageUrl("http")
                .productPrice(3500)
                .description("Tüm ayak tipine uygun ayakkabi")
              //  .subCategories(null)
               // .basketEntityList(null)
                .build();

        ProductElasticSearchModel productElasticSearchModel2 = ProductElasticSearchModel.builder()
                .id(2L)  // Ensure unique IDs
                .name("32 li ülker çikolata gofret")
                .visible(true)
                .imageUrl("http")
                .productPrice(3500)
                .description("32 adet")
              //  .subCategories(null)
              //  .basketEntityList(null)
                .build();

        ProductElasticSearchModel productElasticSearchModel3 = ProductElasticSearchModel.builder()
                .id(3L)  // Ensure unique IDs
                .name("puma ayakkabi")
                .visible(true)
                .imageUrl("http")
                .productPrice(8000)
                .description("koşu ayakkabısı")
             //   .subCategories(null)
              //  .basketEntityList(null)
                .build();

        ProductElasticSearchModel productElasticSearchModel4 = ProductElasticSearchModel.builder()
                .id(4L)  // Ensure unique IDs
                .name("rolex saat")
                .visible(true)
                .imageUrl("http")
                .productPrice(3500)
                .description("Tüm kol ölçüleri için")
              //  .subCategories(null)
              //  .basketEntityList(null)
                .build();

        // Save products to Elasticsearch
        searchProductRepository.save(productElasticSearchModel1);
        searchProductRepository.save(productElasticSearchModel2);
        searchProductRepository.save(productElasticSearchModel3);
        searchProductRepository.save(productElasticSearchModel4);
    }


}
