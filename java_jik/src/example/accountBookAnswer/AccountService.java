package example.accountBookAnswer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public interface AccountService {
	void insertItem(ArrayList<Item>list, Item item); //가계부에 있는 내역 리스트, 추가할 아이템
	void printItem(ArrayList<Item>list); //가계부에 있는 내역 리스트
	void printItem(ArrayList<Item>list, int ...dates); //년월일 검색용
	void printItem(ArrayList<Item>list, Predicate<Item>p); //년월일 검색용
	boolean updateItem(ArrayList<Item>list, int index, Item item); //가계부에 있는 내역 리스트, 몇 번지, 수정할 아이템
	boolean deleteItem(ArrayList<Item>list, int index); //가계부에 있는 내역 리스트, 몇 번지
	void printMenu();
	void runMenu(ArrayList<Item> list, int menu, Scanner scan) throws ParseException;
	Item inputItem(Scanner scan) throws ParseException;
	int selectItem(ArrayList<Item>list, String date, Scanner scan);
	String inputDate(Scanner scan) throws ParseException;
	
	int inputYear(Scanner scan);
	int inputMonth(Scanner scan);
	int inputDay(Scanner scan);
	void printSubMenu();
	void runPrint(ArrayList<Item>list, int subMenu, Scanner scan);
}
