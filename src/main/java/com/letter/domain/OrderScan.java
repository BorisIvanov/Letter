package com.letter.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "LT_ORDER_SCAN")
public class OrderScan {

    @Id
    @GenericGenerator(
            name = "order-foreign",
            strategy = "foreign",
            parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "order")})
    @GeneratedValue(generator = "order-foreign")
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDER_SCAN")
    private byte[] scan;


    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private Order order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getScan() {
        return scan;
    }

    public void setScan(byte[] scan) {
        this.scan = scan;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
