package kr.kh.boot.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.kh.boot.vo.MemberVO;

@Mapper
public interface MemberDAO {

	MemberVO selectMember(String id);

}
