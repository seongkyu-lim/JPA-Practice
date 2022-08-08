package com.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {
   @Id
   @GeneratedValue
   @Column(name = "ORDER_ITEM_ID")
   private Long id;

   @Column(name = "ITEM_ID")
    private Long ItemId;

   @Column(name = "ORDER_ID")
   private Long OrderId;

   private int orderPrice;
   private int count;

   public int getOrderPrice() {
      return orderPrice;
   }

   public void setOrderPrice(int orderPrice) {
      this.orderPrice = orderPrice;
   }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }
}
