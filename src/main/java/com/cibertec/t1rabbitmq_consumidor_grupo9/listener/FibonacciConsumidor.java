package com.cibertec.t1rabbitmq_consumidor_grupo9.listener;

import com.cibertec.t1rabbitmq_consumidor_grupo9.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
@Component
public class FibonacciConsumidor {

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void recibirNumeros(String cadenaNumeros) {
        log.info("Mensaje recibido: {}", cadenaNumeros);

        // Paso 3: convertir la cadena en lista de números
        Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        log.info("Números convertidos: {}", Arrays.toString(integerArray));

        // Paso 5 (Andre): inyectar FibonacciService, calcular,
        // pausa de 20 segundos e imprimir el resultado
    }
}