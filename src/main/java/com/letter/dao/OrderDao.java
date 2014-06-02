package com.letter.dao;

import com.letter.domain.Order;

import java.util.List;


public interface OrderDao {

    public void add(Order order);

    public void publish(long id);

    public List<Order> getAll();

    public Order get(long id);

}
