package com.nuclei.example.repo;

import com.nuclei.example.entity.InventoryEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Inventory repo.
 */
public interface InventoryRepo extends CrudRepository<InventoryEntity, Integer> {

}
