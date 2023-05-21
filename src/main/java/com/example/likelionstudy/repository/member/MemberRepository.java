package com.example.likelionstudy.repository.member;

import com.example.likelionstudy.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>/*, MemberRepositoryCustom*/ {
}