package kr.kh.spring.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SseController {
	
	//private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	
	@GetMapping("/sse/connect")
	public SseEmitter connet() {
		SseEmitter emitter = new SseEmitter(100L*60*30);
		try {
			emitter.send(SseEmitter.event().name("connect").data(emitter));
			return emitter;
		}catch(IOException e) {
			emitter.completeWithError(e);
			return emitter;
		}
	}
	
}