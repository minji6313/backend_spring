package com.jpabook.jpashop.service;


import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //JPA의 모든 데이터 변경이나 로직은 가급적 Transactional 안에서 실행되어야 함 (
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원 가입 save() - 중복회원검증도 같이 작성해보기
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증 검증 후 문제가 없다면 save가 실행되고, return 값으로 해당 id를 member에 전달
        memberRepository.save(member);
        return member.getId();
    }

    //기본적인 중복값 검증을 위한 메서드
    private void validateDuplicateMember(Member member) {
        //예외 처리가 필요

        List<Member> findMembers = memberRepository.findByName(member.getName());
        //만약 가입된 멤버가 아니면 (비어있는 값이 아니라면) 이미 존재하는 회원이라는 예외처리 진행
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } 
    }


    //회원 전체 조회 findAll()
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    //1명의 회원 id 조회 (단건 조회)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
