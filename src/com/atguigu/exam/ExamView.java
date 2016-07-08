package com.atguigu.exam;

import java.io.*;
import java.util.Scanner;

public class ExamView {
	private ItemService itemService=new ItemService();
	private char[] answer;
	
	public ExamView(){
		answer=new char[itemService.TOTAL_ITEMS];
	}


	
	/**
	 * 主菜单
	 */
	public void enterMainMenu(){
		boolean loopFlag = true;
		do{
			displayMainMenu();
			char key = getUserAction();
			switch(key){
				case '1':
					//进入考试
					testExam();
					break;
				case '2':
					//查看上一次考试成绩
					reviewLastExam();
					break;
				case '3':
					System.out.print("确认是否退出(Y/N):");
					char yn = getUserAction();
					if(yn == 'Y'){
						loopFlag = false;
					}
					break;
			}
		}while(loopFlag);
	}
	

	private void displayMainMenu(){
        System.out.println();
        System.out.println();
        System.out.println("-------欢迎使用在线考试系统-------");
        System.out.println();
        System.out.println("       1 进入考试");
        System.out.println("       2 查看成绩");
        System.out.println("       3 系统退出");
        System.out.println();
        System.out.print("       请选择...");
	}
	
	/**
	 * 获取键盘键入的指定值,
	 * @return
	 */
	public char getUserAction() {

		char[] validKey={'1','2','3','A','B','C','D','N','P','F','Y'};
		Scanner scan=new Scanner(System.in);
		char key=0;
		while(true){
			key=scan.next().toUpperCase().charAt(0);
			
			for(char k:validKey){
				if(k==key){
					return key;
				}
			}
		}
	}
	/**
	 *  根据指定参数打印出对应的题目
	 * @param no
	 */
	public void displayItem(int no){
		if(no<=0||no>itemService.TOTAL_ITEMS){
			throw new RuntimeException("没有指定的题目");
		}
		Item item=itemService.getItem(no);
		System.out.println(item.getQuestion());
		
		if(answer[no-1]!='\u0000'){
			System.out.println("您的答案是:"+answer[no-1]);
		}
	}

	public void displayWelcomeInfo(){
		System.out.println("----欢迎进入考试----");
		System.out.println("全部为单选题目 请选择A.B.C.D其中一个");
		System.out.println("请选择(N-下一题  P-上一题):");
		System.out.println("输入N开始考试！ 输入F表示结束考试.");
		
		while(true){
			char key=getUserAction();		
			if(key=='N'){
				return;
			}
		}
	}
	/**
	 * 
	 */
	public void testExam(){	
		int count=1;

		displayWelcomeInfo();
		
		
		while(true){	
			displayItem(count);
			char key=getUserAction();
			
			switch(key){
			case 'A':
			case 'B':
			case 'C':
			case 'D':
				answer[count-1]=key;
			case 'N':				
				if(count>= itemService.TOTAL_ITEMS){
					System.out.println("已经到达最后一题！");
	//				char k=getUserAction();
/*					if(k=='N'){						
						itemService.saveAnswer(answer);
						return;
					}*/
				}else{
					count++;
				}
				break;
			
			case 'P':
				if(count<=1){
					System.out.println("已经到达第一题了！");
				}else{					
					count--;					
				}
				break;
			case 'F':
				System.out.print("是否确定要结束考试(Y/N):");
				char yn=getUserAction();

				if(yn=='Y'){
					itemService.saveAnswer(answer);
					displayScore(answer);
					return;
				}
				
			default:
				System.out.println("输入有误");
				break;
			}
		}
			
	}
	public void displayScore(char[] answer){
		int score=0;
		for(int i=0;i<itemService.TOTAL_ITEMS;i++){
			if(itemService.getItem(i+1).getAnswer()==answer[i]){
				score+=10;
			}
		}
		System.out.println("序  号\t正确答案\t您的答案");
		for(int i=0;i<itemService.TOTAL_ITEMS;i++){
			Item item=itemService.getItem(i+1);
			System.out.println((i+1)+"\t"+item.getAnswer()+"\t"+answer[i]);
		}
		System.out.println("您的成绩为"+score+"分");
	}
	 
	public void reviewLastExam(){
		char[] answer = itemService.readAnswer();
		displayScore(answer);
	}
}
