package com.example.likelionstudy.service.member;


import com.example.likelionstudy.dto.Member;

import java.util.List;

public interface MemberService {

    public void save(Member member);

    public Member findById(Long id);

    public List<Member> findAll();
}
