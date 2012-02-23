package task5;

import java.io.*;
import java.util.*;

public class NameCounts {
	public static void main(String[] args ) {
		String peopleName = "test";
		HashMap listOfName = new HashMap(); //����� ����, �� ���� = ���, � �������� = �-�� ������������ ���
		Set key = new HashSet(); //�����, �� �� ����㳺�� ������ � ������
		
		//������� ����� ����� (peopleName)  � ������
		try{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //����� ��� ����������
		//����������� � ���� ���� �� �������� ������ �����
		while (!(peopleName.equals(""))){
			System.out.print("Enter name: ");
			peopleName = in.readLine(); //������� ���
			if (!(peopleName.equals(""))) {
				Object obj = listOfName.get(peopleName); //������ �������� ��� �����/�������� ���
				//���������� ��������, ���� �����, ������� ������� �����, �������� 1
				if (obj == null) listOfName.put(peopleName, 1);
				//������ �� �������� ������ 1 
				else listOfName.put(peopleName, ((Integer) obj) + 1);
				key.add(peopleName); //�������� ���� � ������� ������
			}
			
		}
		
		System.out.println("-=Count of name: =-");
		
		Object[] arrayOfKey = new Object[key.size()]; //����� ������ ������� ������� ������
		arrayOfKey = key.toArray(); //�������� � ����� ����� �� �����
		//�������� ���������� ������ �������� �� ��������� ������ ������
		for (int i = 0; i < arrayOfKey.length; i++){
			System.out.println(arrayOfKey[i] + ": " + listOfName.get(arrayOfKey[i]));
		}
		
		}
		catch(IOException e){
	    	System.out.println("I/O Error!");		
		}
	}
}
