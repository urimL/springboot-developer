package me.urim.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//해당 인터페이스는 DB에서 데이터 가져오는 퍼시스턴트 계층 역할을 한다. =
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

}
