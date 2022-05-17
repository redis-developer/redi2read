package com.redislabs.edu.redi2read.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@RedisHash
public class BookRating {
  @Id
  private String id;

  @NotNull
  @Reference
  private User user;

  @NotNull
  @Reference
  private Book book;

  @NotNull
  private Integer rating;
}
