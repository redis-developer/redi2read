package com.redislabs.edu.redi2read.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redislabs.edu.redi2read.models.Book;
import com.redislabs.edu.redi2read.models.Category;
import com.redislabs.edu.redi2read.repositories.BookRepository;
import com.redislabs.edu.redi2read.repositories.CategoryRepository;
import com.redislabs.lettusearch.RediSearchCommands;
import com.redislabs.lettusearch.SearchResults;
import com.redislabs.lettusearch.StatefulRediSearchConnection;
import com.redislabs.lettusearch.Suggestion;
import com.redislabs.lettusearch.SuggetOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public ResponseEntity<Map<String, Object>> all( //
      @RequestParam(defaultValue = "0") Integer page, //
      @RequestParam(defaultValue = "10") Integer size //
  ) {
    Pageable paging = PageRequest.of(page, size);
    Page<Book> pagedResult = bookRepository.findAll(paging);
    List<Book> books = pagedResult.hasContent() ? pagedResult.getContent() : Collections.emptyList();

    Map<String, Object> response = new HashMap<>();
    response.put("books", books);
    response.put("page", pagedResult.getNumber());
    response.put("pages", pagedResult.getTotalPages());
    response.put("total", pagedResult.getTotalElements());

    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/categories")
  public Iterable<Category> getCategories() {
    return categoryRepository.findAll();
  }

  @GetMapping("/{isbn}")
  public Book get(@PathVariable("isbn") String isbn) {
    return bookRepository.findById(isbn).get();
  }

  @Value("${app.booksSearchIndexName}")
  private String searchIndexName;

  @Autowired
	private StatefulRediSearchConnection<String, String> searchConnection;

  @GetMapping("/search")
  @Cacheable("book-search")
  public SearchResults<String,String> search(@RequestParam(name="q")String query) {
    RediSearchCommands<String, String> commands = searchConnection.sync();
    SearchResults<String, String> results = commands.search(searchIndexName, query);
    return results;
  }

  @Value("${app.autoCompleteKey}")
  private String autoCompleteKey;

  @GetMapping("/authors")
  public List<Suggestion<String>> authorAutoComplete(@RequestParam(name="q")String query) {
    RediSearchCommands<String, String> commands = searchConnection.sync();
    SuggetOptions options = SuggetOptions.builder().max(20L).build();
    return commands.sugget(autoCompleteKey, query, options);
  }
}
