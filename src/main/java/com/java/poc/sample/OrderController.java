package com.java.poc.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderapi")
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/list-orders")
    public List<Order> OrderList(){
        return orderRepository.findAll();
    }

    @GetMapping("/{orderId}")
    public Order OrderListById(@PathVariable String orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    @PostMapping("/create-order")
    public Order CreadteOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PutMapping("/update-order")
    public Order UpdateOrder(@RequestBody Order newOrder){
        Order oldOrder = orderRepository.findById(newOrder.getOrderId()).orElse(null);
        oldOrder.setOrderName(newOrder.getOrderName());
        return oldOrder;
    }

    @DeleteMapping("/delete-order/{orderId}")
    public String DeleteOrder(@PathVariable String orderId){
        orderRepository.deleteById(orderId);
        return orderId;

    }

}
