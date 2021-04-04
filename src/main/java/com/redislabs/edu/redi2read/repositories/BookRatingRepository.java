package com.redislabs.edu.redi2read.repositories;

import com.redislabs.edu.redi2read.models.BookRating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRatingRepository extends CrudRepository<BookRating, String> {
}
