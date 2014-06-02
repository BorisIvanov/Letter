package com.letter.service;

import com.letter.domain.Order;

import java.util.List;


public interface OrderService {

    public void add(Order order);

    public void publish(long id);

    public List<Order> getAll();

    public Order get(long id);
}
