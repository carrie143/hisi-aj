package com.hisi.common.websocket;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

	@Resource
	WebSocketService webSocketService;

	@MessageMapping("/unpack")
	// @MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
	@SendTo("/topic/unpack")
	// 如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
	public WiselyResponse say(WiselyMessage message) throws Exception {
		// if (!Constant.unpackUsers.contains(message.getName()))
		// Constant.unpackUsers.add(message.getName());//
		// 此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
		// webSocketService.send2Users(Constant.unpackUsers, new WiselyResponse(
		// message.getName() + " Join"));

		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	@MessageMapping("/newOrder")
	// @MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
	@SendTo("/topic/order")
	// 如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
	public WiselyResponse getAddOrder(WiselyMessage message) throws Exception {
		/*
		 * HisiOrderinfoBasic order=new HisiOrderinfoBasic();
		 * order.setOrderId(message.getName());
		 */
		return new WiselyResponse(message.getName());
	}

}