package task1;

import java.io.*;

public class WordCount {
	public static void main(String[] args ) {
		String fileName = "D:\\lear.txt";
		String line;
		RandomAccessFile fio;
		//Зчитуємо імя файлу з консолі
		/*
		try{
		System.out.print("Enter file name: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		fileName = in.readLine();
		}
		catch(IOException e){
	    	System.out.println("I/O Error!");		
		}
		*/
		
		int countCharWS = 0; //к-ть символів без пробілу
		int countChar = 0; //к-ть символів з пробілом
		int countWord = 0; //к-ть слів
		int countLine = 0; //к-ть ліній
		
		File file = new File(fileName);
		
		try{
			System.out.println("Work with file: " + fileName);
			System.out.println("-=Text of File: -=");
			fio = new RandomAccessFile(file, "r");
			long sizeOfFile = fio.length(); //розмір файлу
			
			
			//поки не кінцець файлу
			while(fio.getFilePointer() != sizeOfFile){ 
				line = fio.readLine(); //зчитуємо з файлу по лініях
				System.out.println(line);
				countWord += calculateWord(line);
				countCharWS += calculateChar(line);
				countChar += line.length(); //збільшуємо к-ть символів на к-ть символів в лінії
				countLine++; //збільшуємо к-ть ліній на 1
			}
			System.out.println("-=Results of porgram: =-");
			System.out.println("Count of char (without space): " + countCharWS);
			System.out.println("Count of char: " + countChar);
			System.out.println("Coutn of word: " + countWord);
			System.out.println("Count of line: " + countLine);
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
	
	//рахуємо слова
	private static long calculateWord(String line){
		long countWord = 1; //рядок починається із слова
		for(int i = 0; i < line.length(); i++){
			char c = line.charAt(i);
			if(Character.isWhitespace(c)) countWord++; //к-ть пробілів = к-ть слів
		}
		return countWord;
	}
	
	
	//рахуємо символи без пробілів
	private static long calculateChar(String line){
		long countChar = 0;
		for(int i = 0; i < line.length(); i++){
			char c = line.charAt(i);
			if(!(Character.isWhitespace(c))) countChar++;
		}
		return countChar;
	}
	
}
