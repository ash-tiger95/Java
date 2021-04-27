package com.hackathon.woofy.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.woofy.entity.Parent;
import com.hackathon.woofy.request.UserRequest;
import com.hackathon.woofy.service.ParentService;
import com.hackathon.woofy.util.BasicResponse;

@RestController
@RequestMapping("/api/v1/parent")
public class ParentController {
	
	@Autowired ParentService parentService;

	@PostMapping("/signup")
	public Object signup(@RequestBody UserRequest userRequest) {

		final BasicResponse basicResponse = new BasicResponse();
		
		try {
			Map<String, Object> map = new HashMap<>();
			Parent parent = new Parent(userRequest);
			Parent result = parentService.saveParent(parent);
			
			map.put("parent", result);
			basicResponse.dataBody = map;
			basicResponse.data = "success";
			basicResponse.status = true;
			
		} catch(Exception e) {
			basicResponse.data = "error";
			basicResponse.status = false;
			e.printStackTrace();
		} finally {
			return new ResponseEntity<>(basicResponse, HttpStatus.OK);
		}
	}
	
	@PostMapping("/auth")
	public String callAPI() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("https://openapi.wooribank.com:444/oai/wb/v1/login/getCellCerti");
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        System.out.println("in1");
        conn.setRequestMethod("POST");
        System.out.println("in2");
        conn.setRequestProperty("appKey", "l7xxJlfHBlb9CA54LNh3tOBuDTDM12dG1N5T");
        conn.setRequestProperty("Content-Type", "application/json");
        
        byte[] body = "{\"dataHeader\": {\"UTZPE_CNCT_IPAD\": \"\",\"UTZPE_CNCT_MCHR_UNQ_ID\": \"\",\"UTZPE_CNCT_TEL_NO_TXT\": \"\",\"UTZPE_CNCT_MCHR_IDF_SRNO\": \"\",\"UTZ_MCHR_OS_DSCD\": \"\",\"UTZ_MCHR_OS_VER_NM\": \"\",\"UTZ_MCHR_MDL_NM\": \"\",\"UTZ_MCHR_APP_VER_NM\": \"\"},\"dataBody\": {\"COMC_DIS\": \"1\",\"HP_NO\": \"01064103518\",\"HP_CRTF_AGR_YN\": \"Y\",\"FNM\": \"¾È¼ºÈ£\",\"RRNO_BFNB\": \"950128\",\"ENCY_RRNO_LSNM\": \"1234567\"}}".getBytes();
        System.out.println("in3" + body);
        
        conn.setFixedLengthStreamingMode(body.length);
        conn.setDoOutput(true);
        System.out.println("in4"+ conn.toString());
        
        
        OutputStream out = conn.getOutputStream();
        out.write(body);
//        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("in5" + out);
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	System.out.println("inin ");
        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        	System.out.println("inin "+rd.toString());
        } else {
        	System.out.println("fail ");
        	rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        	System.out.println("fail " + rd.toString());
        }
        System.out.println("in6");
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        System.out.println("in7");
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        return "test";
	}		
}
