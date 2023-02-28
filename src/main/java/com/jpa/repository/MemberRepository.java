package com.jpa.repository;

import com.jpa.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext // 순수 자바일 때는 entity manager factoryㅇㅔ서 enetity manager를 직접 생성해서 사용해야하지만 Spring을 사용하면 컨테이너가 entity manager를 관리해주며 컨테이너로 부터 가져와 사용해야하나다.
    EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
         return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }
}
