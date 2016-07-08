package com.atguigu.exam;

import java.io.*;
import java.util.*;

public class ItemService {	
	private Item[] items;
	public final int TOTAL_ITEMS;
	


	public ItemService() {	
		List<String> list=readTextFile("C:\\Users\\ck\\Desktop\\4.1-需求说明\\Items.txt");
		TOTAL_ITEMS=list.size()/6;
		 items=new Item[TOTAL_ITEMS];
		
		for(int i=0;i<TOTAL_ITEMS;i++){
			Item it=new Item(list.get(i*6), list.get(i*6+1), list.get(i*6+2), list.get(i*6+3), list.get(i*6+4), list.get(i*6+5).charAt(0));				
			items[i]=it;						
		}
				
	}
	
	 public Item getItem(int no){
		 if(no<=0||no>TOTAL_ITEMS){
			 return null;
		 }
		return  items[no-1];
	 }
	 public void saveAnswer(char[] answer) {
		 ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("answer.dat")));
			 oos.writeObject(answer);
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			try {
				if(oos!=null)
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 }


	private List<String> readTextFile(String filename) {
		 List<String> list=new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(!line.trim().equals("")){
					list.add(line);					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public char[] readAnswer(){
		ObjectInputStream ois = null;
		char[] answer = new char[0];
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\ck\\Desktop\\4.1-需求说明\\Items.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			answer = (char[]) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return answer;
	}

}
