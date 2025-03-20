package com.proa.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proa.app.entities.Client;

//@FeignClient(name="micro-client", url="http://localhost:8081/client")  //<< === si no se usa eureka

@FeignClient(name="micro-client") 			// Configuracion con Eureka ya que le pide la confifuraacion a ureka
public interface IFeingClientM {
	//@GetMapping("/id") 					//<< === si no se usa eureka
	
	@GetMapping("client/id") 				//<< === si cuenta con el balanceador eureka
	Client selectById(@RequestParam long id);

}
