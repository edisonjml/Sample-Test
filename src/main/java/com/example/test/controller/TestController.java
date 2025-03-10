package com.example.test.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.test.model.*;

import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Api
public class TestController {
	
	private List<Customer> customers = new ArrayList<>();
	private int count = 0;
	
	@RequestMapping(value = "/api/v1/account", method = RequestMethod.POST)
	public ResponseEntity<Object> addCustomerAccount(@RequestBody Customer customer, HttpServletRequest request){
		
		Map<String, Object> transactionResponse = new HashMap<>();
		customer.setCustomerNumber(count++);
		
		if(customers.add(customer)) {
			transactionResponse.put("customerNumber", customer.getCustomerNumber());
			transactionResponse.put("transactionStatusCode", HttpStatus.OK.toString().substring(0, 3));
			transactionResponse.put("transactionStatusDescription", "Customer account created");
			return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
		} else {
			transactionResponse.put("transactionStatusCode", HttpStatus.UNAUTHORIZED.toString().substring(0, 3));
			transactionResponse.put("transactionStatusDescription", "Email is required field");
			return new ResponseEntity<>(transactionResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/api/v1/account/{customerNumber}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCustomerNumber(HttpServletRequest request, @PathVariable int customerNumber){
		
		Map<String, Object> transactionResponse = new HashMap<>();
		
		boolean isFound = false;
		for(Customer customer : customers) {
			isFound = customer.getCustomerNumber() == customerNumber;
			if(isFound) {
				this.convertObjectToMap(customer, transactionResponse);
				break;
			}
		}
		if(isFound) {
			transactionResponse.put("transactionStatusCode", HttpStatus.OK.toString().substring(0, 3));
			transactionResponse.put("transactionStatusDescription", "Customer Account Found");
			return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
		} else {
			transactionResponse.put("transactionStatusCode", HttpStatus.UNAUTHORIZED.toString().substring(0, 3));
			transactionResponse.put("transactionStatusDescription", "Customer not Found");
			return new ResponseEntity<>(transactionResponse, HttpStatus.UNAUTHORIZED);
		}
	}
	
	private Map<String, Object> convertObjectToMap(Object object, Map<String, Object> map) {
	    Field[] fields = object.getClass().getDeclaredFields();
	    
	    try {
		    for (Field field: fields) {
		        field.setAccessible(true);
		        map.put(field.getName(), field.get(object));
		    }
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return map;
	}

}
