package tonels.json.test.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book1 {
    private String bookName;
    private String bookAuthor;
    private String bookId;

}
