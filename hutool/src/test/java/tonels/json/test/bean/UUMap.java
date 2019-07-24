package tonels.json.test.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UUMap<T> implements Serializable{

	private T result;

}
