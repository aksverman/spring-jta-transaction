package com.rudra.aks.xa.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rudra.aks.xa.domain.CustomerBO;
import com.rudra.aks.xa.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	@Autowired
	CustomerService		customerService;
	
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public	ResponseEntity<?>	saveCustomer(@RequestBody CustomerBO customer) {
		logger.info("customer controller >>");
		try {
			return new ResponseEntity<Integer>(customerService.save(customer), HttpStatus.OK);
		} catch (Exception e ) {
			logger.error("Exception : " + e);
			return new ResponseEntity<String>("Exception : ", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*@RequestMapping(path = "/search/{columnName}/{searchText}", method = RequestMethod.GET)
	public List<CustomerBO>	search(@PathVariable String columnName, @PathVariable String searchText) {
		return customerService.search(columnName, searchText);
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public	ResponseEntity<?>	delete(@RequestBody CustomerBO customer) {
		logger.info("customer controller >>");
		try {
			customerService.delete(customer);
			return new ResponseEntity<>("Customer info deleted...", HttpStatus.OK);
		} catch (Exception e ) {
			logger.error("Exception : " + e);
			return new ResponseEntity<String>("Exception while deletion: ", HttpStatus.BAD_REQUEST);
		}
	}*/
	
	@GetMapping()
	public String	testRestCall() {
		return "Customer Service Starting ....";
	}
	
	@RequestMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String	uploadFile(@RequestParam("filename") MultipartFile file) throws IOException {
		String fileName = "";
		if(file != null) {
			fileName = file.getName();
			System.out.println(file.getContentType());
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
			
			List<MultipartFile> asList = Arrays.asList(file);
			File f = new File("./src/main/resources/test.txt");
			FileOutputStream outputStream = new FileOutputStream(f);
			outputStream.write(file.getBytes());
			outputStream.close();
			
		}
		return fileName;
	}
	
}
