package com.nuclei.iam.entity;

import com.nuclei.iam.enums.CustomerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * The type Customer entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity implements Serializable {
  
  /**
   * The Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  
  /**
   * The Name.
   */
  private String name;
  
  /**
   * The Email id.
   */
  @Column(unique = true)
  private String emailId;
  /**
   * The Password.
   */
  private String password;
  /**
   * The Otp.
   */
  private String otp;
  
  /**
   * The Is logged in.
   */
  private Boolean isLoggedIn;
  /**
   * The Is signed up.
   */
  private Boolean isSignedUp;
  
  /**
   * The Last login.
   */
  private Date lastLogin;
  
  /**
   * The Otp expired time.
   */
  private Date otpExpiryTime;
  
  /**
   * The Created at.
   */
  @CreationTimestamp
  private Date createdAt;
  /**
   * The Updated at.
   */
  @UpdateTimestamp
  private Date updatedAt;
  
  /**
   * The Roles.
   */
  @ElementCollection
  private Set<CustomerRole> roles;
  
  /**
   * Instantiates a new Customer entity.
   *
   * @param name     the name
   * @param emailId  the email id
   * @param password the password
   */
  public CustomerEntity(final String name, final String emailId, final String password) {
    this.name = name;
    this.emailId = emailId;
    this.password = password;
  }
  
  /**
   * To string string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "CustomerEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", emailId='" + emailId + '\'' +
        ", password='" + password + '\'' +
        ", otp='" + otp + '\'' +
        ", isLoggedIn=" + isLoggedIn +
        ", isSignedUp=" + isSignedUp +
        ", lastLogin=" + lastLogin +
        ", otpExpiryTime=" + otpExpiryTime +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", roles=" + roles +
        '}';
  }
}