package com.jpa.service;

import com.jpa.domain.Member;
import com.jpa.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 서비스 로직 테스트")
    void join() throws Exception {
        //given
        Member member = new Member();
        member.setName("lim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("회원가입시 중복된 name일 경우 예외 발생")
    void duplicatedMember(){
        IllegalAccessException thrown = assertThrows(IllegalAccessException.class, () -> {
            //given
            Member member1 = new Member();
            member1.setName("lim");

            Member member2 = new Member();
            member2.setName("lim");

            //when
            memberService.join(member1);
            memberService.join(member2);
        });

        //then
        Assertions.assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }


}
