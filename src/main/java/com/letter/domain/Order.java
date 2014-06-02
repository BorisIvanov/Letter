package com.letter.domain;

import com.letter.helpers.ScanContentType;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "LT_ORDER")
public class Order {

    public Order() {
    }

    public Order(OrderScan orderScan) {
        this.orderScan = orderScan;
        this.orderScan.setOrder(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDER_NUM")
    @NotEmpty(message = "NotEmpty.order.num")
    @Size(max = 20, message = "Size.order.num")
    private String num;

    @Column(name = "ORDER_DATE")
    private Date date;

    @Column(name = "ORDER_NAME")
    @NotEmpty(message = "NotEmpty.order.name")
    @Size(max = 250, message = "Size.order.name")
    private String name;

    @Column(name = "ORDER_PUBLISH", columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean publish = false;

    @Column(name = "ORDER_SCAN_TYPE")
    private String scanType;

    @Column(name = "ORDER_DESC")
    @Size(max = 1000, message = "Size.order.desc")
    private String desc;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private OrderScan orderScan;

    /* save
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if(getOrderScan()!=null){
            getOrderScan().setId(id);
        }
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public OrderScan getOrderScan() {
        return orderScan;
    }

    public void setOrderScan(OrderScan orderScan) {
        this.orderScan = orderScan;
    }


    public String getExt() {
        return ScanContentType.getExt(getScanType());
    }
}
