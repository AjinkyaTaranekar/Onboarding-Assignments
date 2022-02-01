package com.nuclei.example.repo;

import com.nuclei.example.entity.InventoryEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * The interface Inventory repo.
 */
public interface InventoryRepo extends ElasticsearchRepository<InventoryEntity, String> {
  
  @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name\"], \"fuzziness\" : \"AUTO\", \"prefix_length\" : 2}}")
  List<InventoryEntity> searchInInventory (String query);
}
