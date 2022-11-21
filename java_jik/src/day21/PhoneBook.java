package day21;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PhoneBook {
	private String name, company;
	ArrayList<PhoneNumber> pnList;
	public PhoneBook(String name, String company, ArrayList<PhoneNumber> pnList) {
		this.name = name;
		this.company = company;
		this.pnList = pnList;
	}
	public void print() {
		System.out.println("=================");
		System.out.println("이름: "+name+" , 직장: "+company);
		for(PhoneNumber tmp : pnList) {
			System.out.println(tmp);
		}
	}
	public void update(String name, String company) {
		this.name=name;
		this.company=company;
	}
	@Override
	public String toString() {
		return "이름: " + name + ", 직장: " + company + ", " + pnList + "]";
	}
	public void printPhoneNumbers() {
		for(int i=0; i<pnList.size(); i++) {
			System.out.println(i+1+". "+pnList.get(i).toString());
		}
	}
	
}
