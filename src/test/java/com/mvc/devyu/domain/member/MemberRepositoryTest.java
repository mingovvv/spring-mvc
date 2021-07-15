package com.mvc.devyu.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void getInstance() {
    }

    @Test
    void save() {
        // [Given]
        Member m = new Member("devyu", 26);
        // [When]
        Member savedMember = memberRepository.save(m);
        // [Then]
        Member byId = memberRepository.findById(savedMember.getId());

        Assertions.assertThat(byId.getAge()).isEqualTo(26);
        Assertions.assertThat(byId.getUsername()).isEqualTo("devyu");

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        // [Given]
        Member mem1 = new Member("mem1", 42);
        Member mem2 = new Member("mem2", 46);
        Member mem3 = new Member("mem3", 32);
        memberRepository.save(mem1);
        memberRepository.save(mem2);
        memberRepository.save(mem3);
        // [When]
        List<Member> all = memberRepository.findAll();
        // [Then]
        Assertions.assertThat(all.size()).isEqualTo(3);
        Assertions.assertThat(all).contains(mem1, mem2, mem3);

    }

    @Test
    void clearStore() {
    }
}