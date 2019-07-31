package dockerDemo1.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book {

    private String id;

    private String name;

    private String author;

    private String price;

}
