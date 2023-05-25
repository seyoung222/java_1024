package kr.kh.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.boot.dao.MemberDAO;
import kr.kh.boot.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {
	@Autowired
	private MemberDAO memberDao;

	@Override
	public MemberVO getMember(String id) {
		return memberDao.selectMember(id);
	}
	
}
