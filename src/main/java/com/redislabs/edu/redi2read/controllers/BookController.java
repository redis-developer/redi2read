package com.redislabs.edu.redi2read.controllers;

import com.redislabs.edu.redi2read.models.Book;
import com.redislabs.edu.redi2read.models.Category;
import com.redislabs.edu.redi2read.repositories.BookRepository;
import com.redislabs.edu.redi2read.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public Iterable<Book> all() {
    return bookRepository.findAll();
  }

  @GetMapping("/categories")
  public Iterable<Category> getCategories() {
    return categoryRepository.findAll();
  }

  @GetMapping("/{isbn}")
  public Book get(@PathVariable("isbn") String isbn) {
    return bookRepository.findById(isbn).get();
  }
}
