package com.online.DiagnosticCenter.Service;

import com.online.DiagnosticCenter.Entity.*;
import com.online.DiagnosticCenter.Entity.Package;
import com.online.DiagnosticCenter.Exception.InvalidDataException;
import com.online.DiagnosticCenter.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PackageService packageService;

    @Autowired
    private BookingService bookingService;
    public List<Cart> findCart(Long userId) {
        List<Cart> bookingEntities =  cartRepository.findByUserId(userId);
        List<Cart> bookingEntityList = new ArrayList<>();
        for(Cart bookingEntity: bookingEntities){
            Package packageEntity = packageService.findPackage(bookingEntity.getPackageId());
            bookingEntityList.add(setDetails(bookingEntity,packageEntity));
        }
        return bookingEntityList;
    }



    public Cart findOneCart(Long id) {


        return cartRepository.findById(id).get();
    }
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);

    }

    public List<Cart> deleteUserCartByUserId(Long userId) {
        List<Cart> cart = this.findCart(userId);
        for(Cart cart1:cart){
            this.cartRepository.delete(cart1);
        }

        return cart;
    }

    public Cart deleteCartById(Long id) {
        Cart cart = this.findOneCart(id);
        this.cartRepository.delete(cart);
        return cart;
    }

    public Cart setDetails(Cart cart,Package  packageEntity){
        cart.setDescription(packageEntity.getDescription());
        cart.setOfferFrom(packageEntity.getOfferFrom());
        cart.setOfferTo(packageEntity.getOfferTo());
        cart.setImagePath(packageEntity.getImagePath());
        cart.setPackageCost(packageEntity.getPackageCost());
        cart.setNoOfPeople(packageEntity.getNoOfPeople());
        cart.setPackageLocation(packageEntity.getPackageLocation());
        cart.setPackageName(packageEntity.getPackageName());
        cart.setPackageId(packageEntity.getId());
        cart.setSlotCount(packageEntity.getSlotCount());
        cart.setStatus(packageEntity.getStatus());
    return cart;
    }

    public Cart storeCart(Cart cart) {

        Package packageEntity = packageService.findPackage(cart.getPackageId());
        cart = setDetails(cart,packageEntity);
        int availableSlots = packageEntity.getSlotCount();
        int noOfSlots = cart.getQuantity();

        // check slots available or not & noOfSlots are more than available slots
        if (availableSlots <= 0 || availableSlots < noOfSlots) {
            throw new InvalidDataException("selected quantity of slots not available");
        } else {

//            packageEntity.setSlotCount(packageEntity.getSlotCount() - noOfSlots);
            cart.setCost(bookingService.calculateTotalBill(noOfSlots, packageEntity.getId()));
            return cartRepository.save(cart);


        }
    }
}
