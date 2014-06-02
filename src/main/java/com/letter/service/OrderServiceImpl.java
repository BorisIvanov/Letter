package com.letter.service;


import com.letter.dao.OrderDao;
import com.letter.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;


    public void add(Order order) {
        orderDao.add(order);
    }


    public List<Order> getAll() {
        return orderDao.getAll();
    }


    public void publish(long id) {
        orderDao.publish(id);
    }

    public Order get(long id) {
        return orderDao.get(id);
    }

}
