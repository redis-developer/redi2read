package com.redislabs.edu.redi2read.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonIgnoreProperties({ "password", "passwordConfirm" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
@RedisHash
public class User {

  @Id
  @ToString.Include
  private String id;

  @NotNull
  @Size(min = 2, max = 48)
  @ToString.Include
  private String name;

  @NotNull
  @Email
  @EqualsAndHashCode.Include
  @ToString.Include
  @Indexed
  private String email;

  @NotNull
  private String password;

  @Transient
  private String passwordConfirm;

  @Reference
  private Set<Role> roles = new HashSet<Role>();

  public void addRole(Role role) {
    roles.add(role);
  }
}
