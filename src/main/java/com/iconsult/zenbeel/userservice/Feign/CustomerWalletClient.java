//package com.zenbeel.configServer.userservice.Feign;
//
//import com.zenbeel.configServer.userservice.model.entity.Customer;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.ws.rs.Consumes;
//
//@FeignClient(url = "http://localhost:8082", value = "wallet-service")
//public interface CustomerWalletClient {
//    @PostMapping("IconsultAPI/webresources/zambeen/createwallet")
//    @Consumes("application/json")
//    ResponseEntity<Customer> register(@RequestBody Customer customer);
//}
