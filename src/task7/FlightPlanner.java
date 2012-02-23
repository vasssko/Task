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
	
	private static HashMap listCity = new HashMap(); //����� ���, �� ���� = ����� ����������� ����, 
									  				 //� �������� = ����� � �������� ������, ���������� ��������';' 
	private static Set keyCity = new HashSet(); 	 //�����, �� �� ����㳺�� ������ � ������
				
	
	public static void main(String[] args){
		
		AnalizeFile(fileName);
		//��� �������� �������� ����� �� ������� �� ������
		Object[] arrayOfKey = new Object[keyCity.size()]; //����� ������ �� ������� ������� ������
		arrayOfKey = keyCity.toArray(); //�������� � ����� ������ �� �����
		
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));//����� ��� ����������
			System.out.println("Welcom to FlightPlanner!");
			System.out.println("Here's a list of all the cities in our database:");
			//�������� ������ ���������� ���
			for (int i = 0; i < arrayOfKey.length; i++){
				System.out.println(arrayOfKey[i]);
			}
			System.out.println("Let's plan a round-trip route!");
			//������� ��������� ����
			System.out.print("Enter the starting city: ");
			String strStartCity;
			strStartCity = in.readLine();
			//�������� �������� ����������� ���� � ����� StrKey
			strKey = "";
			for (int i = 0; i < strStartCity.length(); i++)
				strKey += strStartCity.charAt(i);
			//����� ��� ����� ��������
			String trip = "";
			trip += strKey;		
			
			//����������� � ���� ���� �� ������� ��������� ����
			do{
				System.out.println("From " + strKey + " you can fly directly to:");
				strCity = "";
				strCity = (String) listCity.get(strKey);
				for (int i = 0; i < strCity.length(); i++){
					if (strCity.charAt(i) != ';') System.out.print(strCity.charAt(i));
					else System.out.println();
				}
				System.out.print("Where do you want to go from "+ strKey + "? ");
				line = in.readLine(); //������� ���� �����������
				boolean fCorrectStrKey = false;
				//��������� �� �������� ���� �����������
				fCorrectStrKey = strCity.contains(line);
				if (fCorrectStrKey){
					strKey = line;
					trip += " -> " + strKey ;
				}	
				else System.out.println("You cann't gat to that city by a direct flight");		
			} while (!(strKey.equals(strStartCity)));
				
			//�������� ���������
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
			//�������� ��� � D:\\flights.txt
			//������� ��� � ����� �� ������, ���� �� ��������� ���� ����
			while(fio.getFilePointer() != sizeOfFile){
				//������� ����� � �����
				line = fio.readLine();
				//���������� �� �� ������ �����
				if (!line.equals("")){
					strKey = "";
					boolean fEndKey = false;
					//�������� �������� �����, �������� ���� � strKey � ������ ���� ����������� � strCity
					for (int i = 0; i < line.length(); i++){
						if ((line.charAt(i) == ' ') && (line.charAt(i+1) == '-') && (line.charAt(i+2) == '>')){
							fEndKey = true;
							i += 4;
						}
						if (!fEndKey) strKey += line.charAt(i);
						else strCity += line.charAt(i);
					}
					//������ ��������� ��� ��� �����������
					strCity += ';';
				}
				else{
					//�������� ���� ����� ��� ������ ����
					listCity.put(strKey, strCity);
					keyCity.add(strKey); //�������� ���� � ������� ������
					//��������� ����� ��� ����������
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
