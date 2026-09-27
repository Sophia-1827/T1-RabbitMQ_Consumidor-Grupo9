package com.cibertec.t1rabbitmq_consumidor_grupo9.rabbitmq;

import com.cibertec.t1rabbitmq_consumidor_grupo9.config.RabbitMqConfig;
import com.cibertec.t1rabbitmq_consumidor_grupo9.service.FibonacciService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Component
public class FibonacciConsumidor {
    private final FibonacciService fibonacciService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void calcularFibonacci(String cadenaNumeros)
      throws InterruptedException
    {
      log.info("Lista recibida de RabbitMQ: {}", cadenaNumeros);

      Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
              .map(String::trim)
              .map(Integer::parseInt)
              .toArray(Integer[]::new);

      Thread.sleep(20000);

      for (Integer numero : integerArray) {
          log.info("Fibonacci({}) = {}", numero, fibonacciService.fibonacci(numero));
      }
      log.info("-----------------------------------------");
    }
}
