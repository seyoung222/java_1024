package kr.kh.spring.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

//첨부파일을 올리면 파일을 그대로 받아오기 위해 필요한 util
public class UploadFileUtils {
	public static boolean removeFile(String uploadPath, String fileName) {//fileName은 fi_name을 의미함
		fileName = fileName.replace("/", File.separator);//폴더 구분자 맞게 변경
		File file = new File(uploadPath + fileName);
		if(file.exists()) {
			return file.delete();
		}
		return false;
	}
	
	///서버에 파일을 업로드하고 업로드 된 경로와 파일명이 합쳐진 문자열을 반환
	public static String uploadFile(String uploadPath, String originalName, byte[] 	
			fileData)throws Exception{
		//UUID(36자=문자32+하이픈4로 8-4-4-4-12 랜덤문자열)생성. 같은 이름의 파일을 올려도 각각 올라가지도록 고유식별역할을 함
		UUID uid = UUID.randomUUID();
		//originalName : 원본 파일명
		//savedName : 서버에 저장될 이름
		String savedName = uid.toString() +"_" + originalName;
		//savedPath : 서버에 저장할 경로 - 년\월\일로 폴더 만들어짐  \2023\02\27
		//uploadPath : 저장할 서버 폴더 위치(= D:\A ). 날짜 폴더 없으면 clacPath가 여기에 폴더를 생성해줌
		String savedPath = calcPath(uploadPath);
		//빈 파일 생성 : 서버에 업로드 될 빈파일을 생성
		File target = new File(uploadPath + savedPath, savedName);
		//복사를 해서 서버에 업로드
		FileCopyUtils.copy(fileData, target);
		//uploadFileName : 서버에 업로드된 경로와 파일명
		//ex) 2023/02/27/uuid_flower.jpg (앞에 D드라이브는 고정된 값이니까 생략)
		String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		return uploadFileName;
	}
	//uploadPath 안에 업로드한 날짜를 기준으로 폴더가 없으면 생성, 있으면 건너뛰어서 경로를 만들어주는 메소드
	private static String calcPath(String uploadPath) {
		//현재 날짜 정보를 가져옴
		Calendar cal = Calendar.getInstance();
		//File.separator은 OS체제에 따른 파일구분기호를 가져옴. 역슬래쉬를 문자로 쓰려면 \를 하나 더써야해서 \\2023
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		// \\2023\\02
		String monthPath = yearPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);//DecimalFormat("00")=두자리 고정
		// \\2023\\02\\27
		String datePath = monthPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		// \\2023, \\2023\\02, \\2023\\02\\27를 이용해 폴더를 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		// \\2023\\02\\27를 리턴
		return datePath;
 
	}
	private static void makeDir(String uploadPath, String... paths) {
		//datePath를 기준으로 폴더가 존재하면 폴더를 만들 필요가 없음
		if(new File(paths[paths.length-1]).exists())
			return;
		//개수가 정해지지않은 매개변수 paths들을 배열로 받아서 향상된 for문 사용
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			//경로에 폴더가 존재하지 않으면 폴더를 생성
			if( !dirPath.exists())
				dirPath.mkdir();
		}
	}
	private static String makeIcon(String uploadPath, String path, String fileName)
        	throws Exception{
		// \\2023\\02\27\\uuid_flower.jpg를 2023/02/27/uuid_flower.jpg로 바꿔줌
		String iconName = path + File.separator + fileName;
		return iconName.replace(File.separatorChar, '/');
	}
}
