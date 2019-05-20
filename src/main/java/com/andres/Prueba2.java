package com.andres;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Prueba2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "Ad24564S3ds";
		ArrayList<String> a = getSumOfNumbers(s);

		for (int i = 0; i < a.size(); i++) {
			String num = a.get(i).toString();
			
		}
		System.out.println(reda(a));
		
		
	}

	public static ArrayList<String> getSumOfNumbers(String s) {
		String [] cadena_arr = s.split("");
		int sumenteros = 0;
		ArrayList<String> numbers2 = new ArrayList<>();
		
		for (String c : cadena_arr) {
			//System.out.println(c);
			if (Pattern.matches("\\d{0,99}",c)) {
				//System.out.println("entero en Array: "+c);
				//System.out.println(arrayNumbers(c));
				//sumenteros += Integer.valueOf(c);
				numbers2.add(c);
			}
			
		}
		return numbers2;
	}
	
	public static String reda(ArrayList<String> array){
		for (int i = 0; i < array.size(); i++) {
			String num = array.get(i).toString();
			return num.concat(num);
		}
		
		return null;
		
	}

}
