package task5;

import java.io.*;
import java.util.*;

public class NameCounts {
	public static void main(String[] args ) {
		String peopleName = "test";
		HashMap listOfName = new HashMap(); //карта імен, де ключ = імя, а значення = к-ть повторювання імя
		Set key = new HashSet(); //ключі, які ми збергіємо окремо в множині
		
		//Зчитуємо імена людей (peopleName)  з консолі
		try{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //буфер для зчитування
		//знаходимось в циклі поки не введений пустий рядок
		while (!(peopleName.equals(""))){
			System.out.print("Enter name: ");
			peopleName = in.readLine(); //зчитуємо імя
			if (!(peopleName.equals(""))) {
				Object obj = listOfName.get(peopleName); //дістаємо значення для ключа/введного імя
				//перевіряємо значення, якщо пусте, значить елемент новий, записуємо 1
				if (obj == null) listOfName.put(peopleName, 1);
				//інакше до значення додаємо 1 
				else listOfName.put(peopleName, ((Integer) obj) + 1);
				key.add(peopleName); //записуємо ключ в множину ключів
			}
			
		}
		
		System.out.println("-=Count of name: =-");
		
		Object[] arrayOfKey = new Object[key.size()]; //масив ключів розміром множини ключів
		arrayOfKey = key.toArray(); //записуємо в масив ключі всі ключі
		//виводимо результати роботи програми за допомогою масиву ключів
		for (int i = 0; i < arrayOfKey.length; i++){
			System.out.println(arrayOfKey[i] + ": " + listOfName.get(arrayOfKey[i]));
		}
		
		}
		catch(IOException e){
	    	System.out.println("I/O Error!");		
		}
	}
}
