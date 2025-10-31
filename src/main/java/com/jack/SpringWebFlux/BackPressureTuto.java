package com.jack.SpringWebFlux;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class BackPressureTuto {
    private Flux<Long> createNoOverflowFlux() {
        return Flux.range(1, Integer.MAX_VALUE)
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100))); // simulate that processing takes time
    }

    private Flux<Long> createOverflowFlux() {
        return Flux.interval(Duration.ofMillis(1))
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)));
    }

    private Flux<Long> createDropOnBackpressureFlux() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop()
                .concatMap(a -> Mono.delay(Duration.ofMillis(100)).thenReturn(a))
                .doOnNext(a -> System.out.println("Element kept by consumer: " + a));
    }

    private Flux<Long> createBufferOnBackpressureFlux() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(50, BufferOverflowStrategy.DROP_LATEST)
                .concatMap(a -> Mono.delay(Duration.ofMillis(100)).thenReturn(a))
                .doOnNext(a -> System.out.println("Element kept by consumer: " + a));
    }


    public static void main(String[] args) {
        BackPressureTuto tutorial = new BackPressureTuto();
//        tutorial.createNoOverflowFlux()
//                .blockLast();
//        tutorial.createOverflowFlux()
//                .blockLast();
//        tutorial.createDropOnBackpressureFlux()
//                .blockLast();
        tutorial.createBufferOnBackpressureFlux()
                .blockLast();
    }
}
