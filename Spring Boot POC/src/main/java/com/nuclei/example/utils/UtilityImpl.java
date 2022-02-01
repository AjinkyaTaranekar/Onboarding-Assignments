package com.nuclei.example.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Utility.
 */
public class UtilityImpl implements Utility {
  
  private static String[] getNullPropertyNames (Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();
    for (java.beans.PropertyDescriptor propertyDescriptor : propertyDescriptors) {
      Object srcValue = src.getPropertyValue(propertyDescriptor.getName());
      if (srcValue == null)
        emptyNames.add(propertyDescriptor.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
  
  @Override
  public void copyNotNullProperties (Object source, Object destination) {
    BeanUtils.copyProperties(source, destination,
        getNullPropertyNames(source));
  }
}
