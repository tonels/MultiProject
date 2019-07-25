package tonels.json.test.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book2 {

    private String book_name;
    private String book_author;
    private String book_id;

//    private String bookId;
}

