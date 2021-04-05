package com.redislabs.edu.redi2read.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {
  private String isbn;
  private Double price;
  private Long quantity;
}
