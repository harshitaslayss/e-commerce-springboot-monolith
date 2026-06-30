package com.app.ecom_app.Repository;

import com.app.ecom_app.Entity.CartItem;
import com.app.ecom_app.Entity.Product;
import com.app.ecom_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);
    List<CartItem> findByUser(User user);

    void deleteByUser(User user);
}
