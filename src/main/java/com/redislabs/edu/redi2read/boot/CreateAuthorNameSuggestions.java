package com.redislabs.edu.redi2read.boot;

import com.redislabs.edu.redi2read.repositories.BookRepository;
import com.redislabs.lettusearch.RediSearchCommands;
import com.redislabs.lettusearch.StatefulRediSearchConnection;
import com.redislabs.lettusearch.Suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(7)
@Slf4j
public class CreateAuthorNameSuggestions  implements CommandLineRunner {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private StatefulRediSearchConnection<String, String> searchConnection;

  @Value("${app.autoCompleteKey}")
  private String autoCompleteKey;

  @Override
  public void run(String... args) throws Exception {
    if (!redisTemplate.hasKey(autoCompleteKey)) {
      RediSearchCommands<String, String> commands = searchConnection.sync();
      bookRepository.findAll().forEach(book -> {
        if (book.getAuthors() != null) {
          book.getAuthors().forEach(author -> {
            Suggestion<String> suggestion = Suggestion.builder(author).score(1d).build();
            commands.sugadd(autoCompleteKey, suggestion);
          });
        }
      });

      log.info(">>>> Created Author Name Suggestions...");
    }
  }
}

