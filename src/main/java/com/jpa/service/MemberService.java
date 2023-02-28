package com.jpa.service;

import com.jpa.domain.Member;
import com.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired //필드 주입
    MemberRepository memberRepository;

    public Long join(Member member) throws IllegalAccessException {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) throws IllegalAccessException {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw  new IllegalAccessException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
