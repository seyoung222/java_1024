package day26;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx01 {

	public static void main(String[] args) {
		InetAddress ia = null;
		//InetAddress 클래스의 static메소드들은 클래스 이름으로 호출, ip주소를 가져올 수 있다.
		try {
			ia = InetAddress.getByName("localhost"); //내부 서버 주소 : 127.0.0.1
			System.out.println(ia);
			ia = InetAddress.getByName("www.naver.com");//도메인 이름을 넣으면 해당사이트의 ip주소 줌
			System.out.println(ia);
			ia = InetAddress.getLocalHost(); //현재 내 PC의 IP주소
			System.out.println(ia); // DESKTOP-0ILOR9O/192.168.10.17
			
			InetAddress []ias = InetAddress.getAllByName("www.naver.com"); //모든 IP주소 가져옴
			for(InetAddress tmp : ias) { 
				System.out.println(tmp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
