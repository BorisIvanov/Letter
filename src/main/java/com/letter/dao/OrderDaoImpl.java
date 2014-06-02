package com.letter.dao;

import com.letter.domain.Order;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@SuppressWarnings("unchecked")
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Order order) {
        getCurrentSession().save(order);
    }

    @Override
    public void publish(long id) {
        Order order = (Order) getCurrentSession().get(Order.class, id);
        if (order.isPublish())
            return;
        order.setPublish(true);
        getCurrentSession().update(order);
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>) getCurrentSession()
                .createCriteria(Order.class, "o")
                .setProjection(Projections.projectionList()
                                .add(Projections.property("o.id"), "id")
                                .add(Projections.property("o.num"), "num")
                                .add(Projections.property("o.name"), "name")
                                .add(Projections.property("o.date"), "date")
                                .add(Projections.property("o.publish"), "publish")
                                .add(Projections.property("o.desc"), "desc")
                                .add(Projections.property("o.scanType"), "scanType")
                )
                .addOrder(org.hibernate.criterion.Order.desc("date"))
                .setResultTransformer(Transformers.aliasToBean(Order.class))
                .list();
    }

    @Override
    public Order get(long id) {
        return (Order) getCurrentSession()
                .createCriteria(Order.class)
                .setFetchMode("orderScan", FetchMode.JOIN)
                .add(Restrictions.eq("id", new Long(id)))
                .uniqueResult();
    }

}
