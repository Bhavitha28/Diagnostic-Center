package com.online.DiagnosticCenter.Controller;


import com.online.DiagnosticCenter.Entity.Booking;
import com.online.DiagnosticCenter.Entity.Cart;
import com.online.DiagnosticCenter.Entity.Location;
import com.online.DiagnosticCenter.Entity.User;
import com.online.DiagnosticCenter.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getOneUserCart/{userId}")
    public List<Cart> findOneUserCart(@PathVariable("userId") Long userId){


        return cartService.findCart(userId);
    }

    @GetMapping("/getOneCart/{id}")
    public Cart findOneCart(@PathVariable("id") Long id) {

        return cartService.findOneCart(id);
    }

    @PostMapping("/AddCart")
    public Cart addCart(@RequestBody Cart cart)  {

        return  cartService.storeCart(cart);
    }

    @PutMapping("/updateCart")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }


    @DeleteMapping("/deleteCart/{id}")
    public Cart deleteCart(@PathVariable("id") Long id) {
        return cartService.deleteCartById(id);
    }


    @DeleteMapping("/deleteUserCart/{userId}")
    public List<Cart> deleteUserCart(@PathVariable("userId") Long userId) {
        return cartService.deleteUserCartByUserId(userId);
    }

}
