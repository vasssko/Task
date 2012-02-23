package task7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FlightPlanner {
	
	private static String fileName = "D:\\flights.txt";
	private static String line;
	private static String strKey = "";
	private static String strCity = "";
	
	private static HashMap listCity = new HashMap(); //карта міст, де ключ = назва початкового міста, 
									  				 //а значення = рядок з кінцевими містами, розділеними символом';' 
	private static Set keyCity = new HashSet(); 	 //ключі, які ми збергіємо окремо в множині
				
	
	public static void main(String[] args){
		
		AnalizeFile(fileName);
		//для зручності записуємо ключі із множини до масиву
		Object[] arrayOfKey = new Object[keyCity.size()]; //масив ключів за розміром множини ключів
		arrayOfKey = keyCity.toArray(); //записуємо в масив ключів усі ключі
		
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//буфер для зчитування
			System.out.println("Welcom to FlightPlanner!");
			System.out.println("Here's a list of all the cities in our database:");
			//виводимо список початкових міст
			for (int i = 0; i < arrayOfKey.length; i++){
				System.out.println(arrayOfKey[i]);
			}
			System.out.println("Let's plan a round-trip route!");
			//зчитуємо початкове місто
			System.out.print("Enter the starting city: ");
			String strStartCity;
			strStartCity = in.readLine();
			//запишемо значення початкового міста в змінну StrKey
			strKey = "";
			for (int i = 0; i < strStartCity.length(); i++)
				strKey += strStartCity.charAt(i);
			//змінна для всьог маршруту
			String trip = "";
			trip += strKey;		
			
			//знаходимось в циклі поки не введене початкове місто
			do{
				System.out.println("From " + strKey + " you can fly directly to:");
				strCity = "";
				strCity = (String) listCity.get(strKey);
				for (int i = 0; i < strCity.length(); i++){
					if (strCity.charAt(i) != ';') System.out.print(strCity.charAt(i));
					else System.out.println();
				}
				System.out.print("Where do you want to go from "+ strKey + "? ");
				line = in.readLine(); //зчитуємо місто призначення
				boolean fCorrectStrKey = false;
				//провіряємо чи коректне місто призначення
				fCorrectStrKey = strCity.contains(line);
				if (fCorrectStrKey){
					strKey = line;
					trip += " -> " + strKey ;
				}	
				else System.out.println("You cann't gat to that city by a direct flight");		
			} while (!(strKey.equals(strStartCity)));
				
			//виводимо результат
			System.out.println("The route, you have choose is:");
			System.out.println(trip);	
		}
		catch(IOException e){
	    	System.out.println("I/O Error!");		
		}
		
	}
	
	private static void AnalizeFile(String fileName){
		
		File file = new File(fileName);
	    RandomAccessFile fio;
		
	    try{
			fio = new RandomAccessFile(file, "r");
			long sizeOfFile = fio.length();
			//обробимо дані з D:\\flights.txt
			//зчитуємо дані з файлу по рядках, поки не досягнемо його кінця
			while(fio.getFilePointer() != sizeOfFile){
				//зчитуємо рядок з файлу
				line = fio.readLine();
				//перевіряємо чи не пустий рядок
				if (!line.equals("")){
					strKey = "";
					boolean fEndKey = false;
					//аналізуємо зчитаний рядок, записуємо ключ в strKey і додаємо місто призначення в strCity
					for (int i = 0; i < line.length(); i++){
						if ((line.charAt(i) == ' ') && (line.charAt(i+1) == '-') && (line.charAt(i+2) == '>')){
							fEndKey = true;
							i += 4;
						}
						if (!fEndKey) strKey += line.charAt(i);
						else strCity += line.charAt(i);
					}
					//додаємо роздільник для міст призначеннь
					strCity += ';';
				}
				else{
					//записуємо блок даних для одного міста
					listCity.put(strKey, strCity);
					keyCity.add(strKey); //записуємо ключ в множину ключів
					//зануляємо рядок міст призначень
					strCity = "";
				}
			}
			fio.close();
		}	
		catch(FileNotFoundException e){
	    	System.out.println("File not found!");
    	}
    	catch(IOException e){
	    	System.out.println("I/O Error!");
    	}
    	catch(Exception e){
	    	System.out.println("Error!");
    	}	
	}
		
}
