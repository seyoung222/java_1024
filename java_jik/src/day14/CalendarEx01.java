package day14;

import java.util.Calendar;

public class CalendarEx01 {

	public static void main(String[] args) {
		/* Calendar 클래스 : 날짜와 관련된 클래스. 
		 * get()을 통해서 년도, 월, 일, 시, 분, 초 등 다양한 정보를 가져옴
		 * 시간을 더하거나/빼거나, 시간단위 추출이 Date보다 쉬움
		 * Date는 시간 출력할때 더 유용
		 * */
		Calendar cal = Calendar.getInstance();
		System.out.println(cal); //이건 모든 필드가 다 나와서 보기 힘드니까 하나씩 부름~
		cal.add(Calendar.MONTH, -1);//add 연산을 통해 쉽게 과거와 미래의 시간을 나타낼수잇음
		
		int year = cal.get(Calendar.YEAR);
		System.out.println(year+"년");
		int month = cal.get(Calendar.MONTH)+1;
		System.out.println(month+"월");
		int date = cal.get(Calendar.DATE);
		System.out.println(date+"일");
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println(hour+"시");
		int min = cal.get(Calendar.MINUTE);
		System.out.println(min+"분");
		int sec = cal.get(Calendar.SECOND);
		System.out.println(sec+"초");
	}
}
