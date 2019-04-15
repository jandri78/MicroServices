package com.andres.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {

		return new PersonV1("Bob Charlie");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {

		return new PersonV2(new Name("Andres", "Perez"));
	}
	
	@GetMapping(value = "/person/param",params = "version=1")
	public PersonV1 personRequest1() {

		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/param",params = "version=2")
	public PersonV2 personRequest2() {

		return new PersonV2(new Name("Andres", "Perez"));
	}
	
	
	
	//Header X-API-VERSION=1"
	
	@GetMapping(value = "/person/header",headers = "X-API-VERSION=1")
	public PersonV1 personRequestHeader() {

		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/header",headers = "X-API-VERSION=2")
	public PersonV2 personRequestHeader2() {

		return new PersonV2(new Name("Andres", "Perez"));
	}
	
	
	
	//Accpet in the Header Accepet = application/andres-v1+json
	
	@GetMapping(value = "/person/produces",produces = "application/andres-v1+json")
	public PersonV1 personRequestProduces() {

		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/produces",produces = "application/andres-v2+json")
	public PersonV2 personRequestProduces2() {

		return new PersonV2(new Name("Andres", "Suarez"));
	}

}
