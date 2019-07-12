package demo3.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sequence")
public class SequenceId {
    @Id
    private String id;

    private long seq;
}
