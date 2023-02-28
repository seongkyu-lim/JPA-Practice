package com.jpa.service;

import com.jpa.domain.Delivery;
import com.jpa.domain.Member;
import com.jpa.domain.Order;
import com.jpa.domain.OrderItem;
import com.jpa.domain.item.Item;
import com.jpa.domain.item.NotEnoughStockException;
import com.jpa.repository.MemberRepository;
import com.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemService itemService;

    public Long order(Long memberId, Long itemId, int count) throws NotEnoughStockException {
        Member member = memberRepository.findOne(memberId);
        Item item = itemService.findOne(itemId);
        Delivery delivery = new Delivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
}
