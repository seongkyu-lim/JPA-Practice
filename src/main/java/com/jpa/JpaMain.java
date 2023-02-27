package com.jpa;

import com.jpa.domain.Member;
import com.jpa.domain.Team;

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
        /*
            3장 영속성 관리.
         */
        String id = "1";
        Member member = new Member();
        member.setId(id);
        member.setName("성규");
        member.setAge(25);

        //생성.
        em.persist(member);

        //수정.
        member.setAge(26);

        //단건 조회.
        Member findMember  = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getName() + ", age = " + findMember.getAge());

        //목록 조회.
        List<Member> memberList =  em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("member.size="+ memberList.size());

        //삭제.
        em.remove(member);

    }

    public static void testSave(EntityManager em){
        /*
        5장 연관관계 매핑.

        매핑 생성.
         */
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public static void queryLogicJoin(EntityManager em){
        /*
        5장 연관관계 매핑.

        jpql로 매핑된 테이블 조회.
         */

        String jpql = "select m from Member m join m.team t where "+"t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList){
            System.out.println("[query] member.username=" +
                    member.getName());
        }
    }

    public static void updateRelation(EntityManager em){
        /*
        5장 연관관계 매핑.

        매핑된 테이블 값 수정.
         */
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    public static void deleteRelation(EntityManager em){
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }
}
