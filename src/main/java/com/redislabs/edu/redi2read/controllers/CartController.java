package com.redislabs.edu.redi2read.controllers;

import com.redislabs.edu.redi2read.models.Cart;
import com.redislabs.edu.redi2read.models.CartItem;
import com.redislabs.edu.redi2read.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping("/{cartId}")
  public Cart get(@PathVariable("cartId") String cartId) {
    return cartService.get(cartId);
  }

  @PostMapping("/{cartId}")
  public void addToCart(@PathVariable("cartId") String cartId, @RequestBody CartItem item) {
    cartService.addToCart(cartId, item);
  }

  @DeleteMapping("/{cartId}/{isbn}")
  public void removeFromCart(@PathVariable("cartId") String cartId, @PathVariable("isbn") String isbn) {
    cartService.removeFromCart(cartId, isbn);
  }

  @PostMapping("/{cartId}/checkout")
  public void checkout(@PathVariable("cartId") String cartId) {
    cartService.checkout(cartId);
  }

}
