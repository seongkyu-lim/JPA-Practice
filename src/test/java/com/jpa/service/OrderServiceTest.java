package com.jpa.service;

import com.jpa.domain.Address;
import com.jpa.domain.Member;
import com.jpa.domain.Order;
import com.jpa.domain.enums.OrderStatus;
import com.jpa.domain.item.Book;
import com.jpa.domain.item.Item;
import com.jpa.domain.item.NotEnoughStockException;
import com.jpa.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품 주문 로직 테스트")
    void createOrder() throws NotEnoughStockException {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 1000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(1000*2, getOrder.getTotalPrice());
        assertEquals(8, item.getStockQuantity());
    }

    @Test
    @DisplayName("상품주문 재고수량초과할 경우 에러를 낸다.")
    void 상품주문_재고수량초과() throws Exception, NotEnoughStockException {
        NotEnoughStockException thrown = assertThrows(NotEnoughStockException.class, ()->{
            //Given
            Member member = createMember();
            Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고

            int orderCount = 200; //재고 보다 많은 수량

            //When
            orderService.order(member.getId(), item.getId(), orderCount);
        });
        //Then
        assertEquals("need more stock", thrown.getMessage());
    }
    @Test
    @DisplayName("주문 취소 로직 테스트.")
    void 주문취소() throws NotEnoughStockException {

        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //When
        orderService.cancelOrder(orderId);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getStockQuantity());
    }


    private Item createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

    private Member createMember(){
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}
