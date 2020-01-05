package samples.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Created by grijesh
 */
public class Consumer_supplier_function {

    @Test
    public void t1(){

        List<String> names = new ArrayList<String>();
        names.add("Harry");
        names.add("Daniel");
        names.add("Lucifer");
        names.add("April O' Neil");

        names.stream().forEach((item)-> {
            printNames(()-> item);
        });
        // Harry
        // Daniel
        // Lucifer
        // April O' Neil
    }



    private static void printNames(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }


}
