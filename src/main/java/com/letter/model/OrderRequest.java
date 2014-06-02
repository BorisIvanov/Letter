package com.letter.model;

import com.letter.domain.Order;
import com.letter.domain.OrderScan;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class OrderRequest extends Order {

    private Order order = new Order(new OrderScan());

    private String dateStr;

    private List<MultipartFile> scanFile;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setScan(byte[] scan) {
        order.getOrderScan().setScan(scan);
    }

    public List<MultipartFile> getScanFile() {
        return scanFile;
    }

    public void setScanFile(List<MultipartFile> scanFile) {
        this.scanFile = scanFile;
    }

    public Order getOrder() {
        order.setNum(getNum());
        order.setDate(getDate());
        order.setName(getName());
        order.setPublish(isPublish());
        order.setScanType(getScanType());
        order.setDesc(getDesc());
        return order;
    }
}
