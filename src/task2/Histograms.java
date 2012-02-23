package task2;

import java.io.*;

public class Histograms {
	
	public static void main(String[] args){
		String fileName = "D:\\MidtermScores.txt";
		String line;
		RandomAccessFile fio;
		
		int[] h = new int[11]; //масив для підрахування к-ті оцінок
				
		File file = new File(fileName);
		
		try{
			System.out.println("Work with file: " + fileName);
			fio = new RandomAccessFile(file, "r");
			long sizeOfFile = fio.length();
			
			//в залежності від того в яких межах знаходиться оцінка, збільшуємо елемент масиву з відповідним індексом на 1		
			while(fio.getFilePointer() != sizeOfFile){
				line = fio.readLine();
				int num = Integer.valueOf(line);
				if ((0 <= num) && (num <= 9)) h[0]++;
				if ((10 <= num) && (num <= 19)) h[1]++;
				if ((20 <= num) && (num <= 29)) h[2]++;
				if ((30 <= num) && (num <= 39)) h[3]++;
				if ((40 <= num) && (num <= 49)) h[4]++;
				if ((50 <= num) && (num <= 59)) h[5]++;
				if ((60 <= num) && (num <= 69)) h[6]++;
				if ((70 <= num) && (num <= 79)) h[7]++;
				if ((80 <= num) && (num <= 89)) h[8]++;
				if ((90 <= num) && (num <= 99)) h[9]++;
				if (num == 100) h[10]++;		
			}
			
			//виводимо результат
			System.out.println("-=Results of porgram: =-");
			for (int i = 0; i < h.length; i++){
				int count = h[i];
				
				if (i != 10) System.out.print(i + "0-" + i + "9: ");
				else System.out.print("  100: ");
				
				for(int j = 0; j < count; j++)
					System.out.print("*");
				
				System.out.print("\n");
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
