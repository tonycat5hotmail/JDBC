package com.atguigu.exam;

import java.io.IOException;
import java.util.*;
/*添加以下功能：程序启动时，显示主菜单，菜单包含以下两项：
进入考试
显示上次考试成绩
当用户选择1时，进入考试过程；当用户选择2时，显示上次考试成绩，按n键后回到主菜单。
至强康神！！！！
 * */
public class Exam {

	public static void main(String[] args) throws IOException {
		/*ItemService is = new ItemService();
		List<String> list = is.readTextFile("./Items.txt");
		
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		Item item = is.getItem(5);
		System.out.println(item);*/
		
		ExamView ev = new ExamView();
		/*while(true){
			char key = ev.getUserAction();
			System.out.println(key);
		}*/
//		ev.testExam();
		
		ev.enterMainMenu();

	}

}
