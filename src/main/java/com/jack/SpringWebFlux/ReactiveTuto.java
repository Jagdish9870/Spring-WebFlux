package com.jack.SpringWebFlux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ReactiveTuto {
    // in case of single data use mono----------
    public Mono<String> test(){
        return Mono.just("jack");
    }
    // more data - use flux publisher
    public Flux<String> test2(){
        return Flux.just("jaggi","jack");
    }
    // list creation
    public Flux<String> testList(){
    List<String> list= Arrays.asList("apple","banana","guava");
     return Flux.fromIterable(list);
    }
    // map creation
    public Flux<String> testMap(){
        Flux<String> flux= Flux.just("apple","banana","guava");
        return flux.map(f->f.toUpperCase(Locale.ROOT));
    }
    // flatmap creation - returns publisher
    public Flux<String> testFlatMap(){
        Flux<String> flux2= Flux.just("table","Chair","Glass");
        return flux2.flatMap(s->Mono.just(s.toLowerCase(Locale.ROOT)));
    }
    // skip
    public Flux<String> testSkip(){
        Flux<String> flux2= Flux.just("table","Chair","Glass");
        return flux2.skip(2);
    }
    // skip with delay
    public Flux<String> testSkipdelay(){
        Flux<String> flux2= Flux.just("banzi","jumping","Glass");
        return flux2.delayElements(Duration.ofSeconds(2));
    }
    // range
    public Flux<Integer> testRange(){
        Flux<Integer> flux4= Flux.range(10,20);
        return flux4;
    }
    // skip with range
    public Flux<Integer> testSkipRange(){
        Flux<Integer> flux2= Flux.range(1,20);
        return flux2.skipWhile(i-> i<15);
    }
    // concat
    public Flux<Integer> testConcat(){
        Flux<Integer> flux1= Flux.range(1,20);
        Flux<Integer> flux2= Flux.range(20,20);
        Flux<Integer> flux3= Flux.range(15,20);
        return Flux.concat(flux1,flux2,flux3);
    }
    // zip
    public Flux<Tuple2<Integer,Integer>> testZip(){
        Flux<Integer> flux1= Flux.range(1,10);
        Flux<Integer> flux2= Flux.range(20,15);
        return Flux.zip(flux1,flux2);
    }
    // mono to list
    public Mono<List<Integer>> monoToList(){
        Flux<Integer> flux =Flux.range(1,25);
        return flux.collectList();
    }
    public Flux<List<Integer>> testBuffer(){
        Flux<Integer> flux=Flux.range(1,25).delayElements(Duration.ofMillis(1000));
        return flux.buffer(Duration.ofSeconds(3));
    }
    public static void main(String[] args) throws InterruptedException {
        ReactiveTuto reactiveTuto= new ReactiveTuto();
//        reactiveTuto.test().subscribe();
//        reactiveTuto.test2().subscribe(System.out::println);
//        reactiveTuto.testList().subscribe(System.out::println);
//        reactiveTuto.testMap().subscribe(System.out::println);
//        reactiveTuto.testFlatMap().subscribe(System.out::println);
//        reactiveTuto.testSkip().subscribe(System.out::println);
//        reactiveTuto.testSkipdelay().subscribe(System.out::println);
//      Thread.sleep(5000);
//        reactiveTuto.testRange().subscribe(System.out::println);
//        reactiveTuto.testSkipRange().subscribe(System.out::println);
//        reactiveTuto.testConcat().subscribe(System.out::println);
//        reactiveTuto.testZip().subscribe(System.out::println);
//        reactiveTuto.monoToList().subscribe(System.out::println);
        reactiveTuto.testBuffer().subscribe(System.out::println);
       Thread.sleep(30000);
    }
}
