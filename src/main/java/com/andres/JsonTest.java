package com.andres;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;



public class JsonTest {
	private static final List<String> FROBBIDEN_KEYS = Arrays.asList("organizationId", "whitelabelId");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonNode jsonNode = null;
		
		Map<String, Object> cusFields = new HashMap<>();
		cusFields.put("name", "name");
		cusFields.put("lastname", "lastname");
		
		ContactDTO contact = new ContactDTO();
		contact.setId("1");
		contact.setMobileNumber("3188061583");
		contact.setSource("API");		
		contact.setCustomFields(cusFields);
		contact.setJsonNode(jsonNode);
		contact.setOrganizationId("2342342342342");
		contact.setWhitelabelId("1234dasd54a6sd4a56");
		
		ContactDTO contact2 = new ContactDTO();
		contact2.setId("2");
		contact2.setMobileNumber("3889444");
		contact2.setSource("API");		
		contact2.setCustomFields(cusFields);
		contact2.setJsonNode(jsonNode);
		contact2.setOrganizationId("11as5444");
		contact2.setWhitelabelId("qqew874a774");
		
		
		List<ContactDTO> content = new ArrayList<ContactDTO>();
		content.add(contact);
		content.add(contact2);
		
		for (ContactDTO contactDTO : content) {
			System.out.println(contactDTO);
		}
		System.out.println("--------");
		
		List<ContactDTO> content2 = new ArrayList<ContactDTO>();
		
		toJson(contact2);
		
		

	}
	public static Map<String, Object> toJson(ContactDTO contact) {
		for (String key : FROBBIDEN_KEYS) {
			contact.getCustomFields().remove(key);
		}
		return contact.getCustomFields();
	}

	public static List toJson(List<ContactDTO> content) {
		List<Map<String, Object>> result = new ArrayList<>();

		for (ContactDTO contactDTO : content) {
			result.add(toJson(contactDTO));
			
		}
		return result;
	}
}
