package com.jpa.domain;

import com.jpa.domain.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
   @Id
   @GeneratedValue
   @Column(name = "ORDER_ITEM_ID")
   private Long id;

   private int orderPrice;
   private int count;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ORDER_ID")
   private Order order;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name ="ITEM_ID")
   private Item item;

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public Item getItem() {
      return item;
   }

   public void setItem(Item item) {
      this.item = item;
   }

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
