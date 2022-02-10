package com.nuclei.iam.repo;

import com.nuclei.iam.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Customer repo.
 */
public interface CustomerRepo extends CrudRepository<CustomerEntity, Integer> {
  
  /**
   * Find by email id optional.
   *
   * @param emailId the email id
   *
   * @return the optional
   */
  Optional<CustomerEntity> findByEmailId(String emailId);
}
