package samples.lambda;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class Interface1Test {

    @Test
    public void main() {
    }


    @Test
    public void t1(){

        final AtomicInteger ranking = new AtomicInteger();
        for (int i = 0; i < 10 ; i++) {
            System.out.println(ranking.incrementAndGet());

        }
    }


}