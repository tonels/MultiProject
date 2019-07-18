package querydsl.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

/**
 * <p>
 * <b>PageParam Description:</b> 用于接收前端的分页必须参数和排序参数
 * </p>
 */
@Data
@Accessors(chain = true)
public class PageParam {
    public final static String ASC = "asc";
    public final static String DESC = "desc";
    private final static int DEFAULT_ROWS = 20;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 一页纪录数
     */
    private Integer rows;

    /**
     * 排序字段名
     */
    private String sidx;

    /**
     * 排序方式：ase desc
     */
    private String sord;

    /**
     * <p>
     * <b>Title:</b> pageIsCompliant
     * </p>
     * <p>
     * <b>Description:</b> 检查页码和一页记录数是否符合规范（page > 0 && rows > 0）
     */
    public boolean pageIsCompliant() {
        if (page == null || page < 1 || rows == null || rows < 1) {
            return false;
        }
        return true;
    }

    /**
     * <p>
     * <b>Title:</b> orderByIsCompliant
     * </p>
     * <p>
     * <b>Description:</b> 检查排序字段和排序方式是否都不为空
     * </p>
     *
     * @return
     * @author Zewei.Zhou
     */
    public boolean orderByIsCompliant() {
        if (sidx == null || sidx.trim().equals("")) {
            return false;
        }
        if (ASC.equals(sord) || DESC.equals(sord)) {
            return true;
        }
        return false;
    }

    /**
     * <p><b>Title:</b> getPageable</p>
     * <p><b>Description:</b> 得到 Pageable 若不符合规范则返回null</p>
     *
     * @return
     * @author Zewei.Zhou
     */
    @JsonIgnore
    public Pageable getPageable() {
        if (!pageIsCompliant()) {
            return null;
        }
        Pageable pageable = null;
        if (orderByIsCompliant()) {
            pageable = PageRequest.of(page - 1, rows, Sort.by(new Order(Direction.fromString(sord), sidx)));
        } else {
            pageable = PageRequest.of(page - 1, rows);
        }
        return pageable;
    }

    /**
     * <p><b>Title:</b> getPageable</p>
     * <p><b>Description:</b> 得到 Pageable 若不符合规范则返回null</p>
     *
     * @param fields 可排序的字段
     * @return
     * @author Zewei.Zhou
     */
    @JsonIgnore
    public Pageable getPageable(List<String> fields) {
        if (!pageIsCompliant()) {
            return null;
        }
        Pageable pageable = null;
        if (orderByIsCompliant() && fields != null && fields.contains(this.sidx)) {
            pageable = PageRequest.of(page - 1, rows, Sort.by(new Order(Direction.fromString(sord), sidx)));
        } else {
            pageable = PageRequest.of(page - 1, rows);
        }
        return pageable;
    }

    /**
     * <p><b>Title:</b> initPageAndRows</p>
     * <p><b>Description:</b> 初始化page和rows的值</p>
     *
     * @author Zewei.Zhou
     */
    public void initPageAndRows() {
        this.page = 1;
        this.rows = DEFAULT_ROWS;
    }
}
