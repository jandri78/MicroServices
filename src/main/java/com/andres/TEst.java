package com.andres;

import java.util.regex.Pattern;

public class TEst {

	public static void main(String[] args) {
		String s = "57320-3945-4702";

		System.out.println(numberCorrector(s));

	}
	static String noMatcheResponse= "not_found";

	public static String numberCorrector(String StrNumber) {
		
		if (StrNumber != null) {

			if (Pattern.matches("\\s+", StrNumber) || StrNumber.length() < 3 || StrNumber.equals(null)
					|| Pattern.matches("\\D+", StrNumber) || StrNumber.equals("0")
					|| Pattern.compile("[a-zA-Z*]").matcher(StrNumber).find()) {
				StrNumber = noMatcheResponse;
				return StrNumber;
			}else if (Pattern.compile("[-]").matcher(StrNumber).find()) {

				String minusReturn = StrNumber.replace("-", "");
				return patternCleaner(minusReturn);

			} else if (Pattern.compile("[+]").matcher(StrNumber).find()) {

				String plusReturn = StrNumber.replace("+", "");
				return patternCleaner(plusReturn);

			} else {
				StrNumber.trim();
				String spaceResponse = StrNumber.replaceAll("\\s", "");

				if (Pattern.compile("\\A^0").matcher(spaceResponse).find() || spaceResponse.length() < 3) {
					spaceResponse = noMatcheResponse;
					return spaceResponse;
				} else {
					return spaceResponse;
				}

			}
		}else {
			StrNumber = noMatcheResponse;
			return StrNumber;
		}
	}
	
	private static String patternCleaner(String plusReturn) {
		
		if (Pattern.compile("\\s").matcher(plusReturn).find()) {
			return plusReturn.replaceAll("\\s", "");
		} else if (plusReturn.equals("0")||Pattern.compile("\\A^0").matcher(plusReturn).find()||Pattern.compile("[a-zA-Z]").matcher(plusReturn).find()) {
			plusReturn = noMatcheResponse;
			return plusReturn;
		} else {
			return plusReturn;
		}
	}
}
