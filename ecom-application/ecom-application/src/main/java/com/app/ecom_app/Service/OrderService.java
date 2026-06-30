package com.app.ecom_app.Service;

import com.app.ecom_app.DTO.OrderItemDTO;
import com.app.ecom_app.DTO.OrderResponse;
import com.app.ecom_app.Entity.*;
import com.app.ecom_app.Repository.CartItemRepository;
import com.app.ecom_app.Repository.OrderRepository;
import com.app.ecom_app.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public Optional<OrderResponse> createOrder(String userId) {
        //validate cart items
        List<CartItem> cartItems= cartService.getCart(userId);
        if(cartItems.isEmpty()) {return Optional.empty();}

        //validate user
        Optional<User> userOptional= userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty()) {return Optional.empty();}

        User user= userOptional.get();

        //find the order sum:
        BigDecimal totalPrice= cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order= new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);

        //set orderItems
        List<OrderItem> orderItems= cartItems.stream()
                .map(item-> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .toList();

        order.setItems(orderItems);
        Order savedOrder= orderRepository.save(order);
        cartService.clearCart(userId);

        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal (orderItem.getQuantity()))
                                )
                        ).toList(),
                order.getCreatedAt()
        );
    }
}
