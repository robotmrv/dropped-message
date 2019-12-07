package com.example.droppedmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class DroppedMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroppedMessageApplication.class, args);
	}

}

@RequestMapping("/test")
@RestController()
class Ctrl {

	@GetMapping("/compression-off")
	public Mono<?> compressionOff() {
		return getData(1)
				.log("compression-off");
	}

	@GetMapping("/compression-on")
	public Mono<?> compressionOn() {
		return getData(55)
				.log("compression-on");
	}

	private Mono<List<String>> getData(int elements) {
		return Mono.fromSupplier(() -> IntStream.range(0, elements)
				.mapToObj(operand -> operand + "_" + UUID.randomUUID())
				.collect(Collectors.toList())
		);
	}

}
