package com.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em){
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("성규");
        member.setAge(25);

        //생성.
        em.persist(member);

        //수정.
        member.setAge(26);

        //단건 조회.
        Member findMember  = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getUsername() + ", age = " + findMember.getAge());

        //목록 조회.
        List<Member> memberList =  em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("member.size="+ memberList.size());

        //삭제.
        em.remove(member);

    }
}
