package com.netrobot.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WalletController {

	@RequestMapping(value = "/wallet/status", method = RequestMethod.GET)
	public String getWalletStatus() {
		log.debug("Getting wallet status");
		return "OK";
	}
}
