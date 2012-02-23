package task3;

import java.io.*;
import java.util.*;

public class UniqueNames {
	public static void main(String[] args ) {
		String peopleName = "test"; //ініціалізуємо змінну, щоб зайти в цикл
		SortedSet arrayOfUniqName = new TreeSet(); //зберігаємо імена в відсотортованій множині, що гарантує їх унікальність
		
		//read peopleName from consol
		try{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (!(peopleName.equals(""))){
			System.out.print("Enter name: ");
			peopleName = in.readLine();
			if (!(peopleName.equals(""))) arrayOfUniqName.add(peopleName);
		}
		
		System.out.println("-=Unique name list contains: =-");
		Object[] a = new Object[arrayOfUniqName.size()]; //створюємо масив імен за розміром множини
		a = arrayOfUniqName.toArray(); //записуємо в масив усі елементи множини
		//виводимо результати
		for (int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
		
		}
		catch(IOException e){
	    	System.out.println("I/O Error!");		
		}
	}
}
