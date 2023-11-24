package com.cwms.controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ReadURL;
import com.cwms.entities.ShippingDetails;
import com.cwms.service.ShippingDetailsImpl;


@CrossOrigin("*")
@RestController
@RequestMapping("/ShippingDetails")
public class ShippingDetailsController {

	@Autowired
	ShippingDetailsImpl shippingDetailsImpl;


	@GetMapping("/getDetailsAsMap")
	public Map<String, ShippingDetails> getShippingDetailsAsMap() {
		
		List<ShippingDetails> shippingDetailsList = shippingDetailsImpl.getShippingDetails();

		// Convert the list to a HashMap with 'Entity ID' as the key and
		// 'ShippingDetails' object as the value
		Map<String, ShippingDetails> shippingDetailsMap = shippingDetailsList.stream()
				.collect(Collectors.toMap(ShippingDetails::getEntityId, sd -> sd));

		return shippingDetailsMap;
	}

	@GetMapping("/submit")
	public List<ShippingDetails> getMethodName() {

		List<ShippingDetails> sd = shippingDetailsImpl.getShippingDetails();

		return sd;
	}

	@PostMapping(value = "/readurl")
	public Map<String, String> getlink(@RequestBody ReadURL readURL) throws IOException {
		String s = readURL.getLink();
		URL url = new URL(s);
		System.out.println(s);
		int timeoutMillis = 5000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}
		return hashMap;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveShippingDetails(@RequestBody ReadURL readURL) throws IOException {
		
		String s = readURL.getLink();
		URL url = new URL(s);

		int timeoutMillis = 5000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		Map<String, String> dataMap = new LinkedHashMap<>();

		String currentKey = "";
		String Demo = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				currentKey = label.text();

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					dataMap.put(currentKey, "");
				}

			} else {
				String value = label.text();
				dataMap.put(currentKey, value);
			}
		}

		// Print the data in the desired format
		for (Map.Entry<String, String> entry : dataMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		ShippingDetails shippingdetails = new ShippingDetails();
		shippingdetails.setDC_Office(dataMap.get("DC Office:"));
		shippingdetails.setSEZ_Name(dataMap.get("SEZ Name:"));
		shippingdetails.setSEZ_Unit_Developer_Co_Developer(dataMap.get("SEZ Unit / Developer / Co-Developer:"));
		shippingdetails.setImport_Export_Code(dataMap.get("Import-Export Code:"));
		shippingdetails.setEntityId(dataMap.get("Entity ID:"));
		shippingdetails.setRequest_Details(dataMap.get("Request Details:"));
		shippingdetails.setRequest_ID(dataMap.get("Request ID:"));
		shippingdetails.setPort_of_Loading(dataMap.get("Port of Loading:"));
		shippingdetails.setPort_of_Destination(dataMap.get("Port of Destination:"));
		shippingdetails.setCountry_of_Destination(dataMap.get("Country of Destination:"));
		shippingdetails.setSB_No_Date(dataMap.get("SB No & Date:"));
		shippingdetails.setCustom_House_Agent_Name_Code(dataMap.get("Custom House Agent Name & Code:"));
		shippingdetails.setAssessment_Date(dataMap.get("Assessment Date:"));
		shippingdetails.setRequest_Status(dataMap.get("Request Status:"));
		shippingdetails.setConsignment_Details(dataMap.get("Consignment Details:"));
		shippingdetails.setRotation_Number_Date(dataMap.get("Rotation Number & Date:"));
		shippingdetails.setCargo_Details(dataMap.get("Cargo Details:"));
		shippingdetails.setNet_Realisable_Value_in_Rs(dataMap.get("Net Realisable Value in Rs. :"));

		shippingDetailsImpl.create(shippingdetails);

		System.out.println("acess successfully. . .  .");
		return new ResponseEntity<>("Successful String", HttpStatus.OK);
	}
	

}