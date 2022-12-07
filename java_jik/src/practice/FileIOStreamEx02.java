package practice;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOStreamEx02 {

	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("test2.txt");
			char ch = '\u0000';
			for(int i=0; i<200; i++) {
				fw.write(ch++);
			}
			fw.close();
			
			fr= new FileReader("test2.txt");
			int num;
			for(int i=0; i<200; i++) {
				num=fr.read();
				System.out.print((char)num);
			}
			fr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
