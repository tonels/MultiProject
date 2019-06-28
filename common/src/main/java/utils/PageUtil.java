//package utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Map;
//
///**
// * 分页参数处理工具
// *
// */
//@Slf4j
//public class PageUtil {
//
//	public static final int DEFAULT_ROWS = 20;
//	public static final int DEFAULT_START = 0;
//	/**
//	 * 默认排序字段
//	 */
//	public static final String DEFAULT_SIDX = "updatedTime";
//
//	/**
//	 * 分页参数，第几页，从1开始，从前端传过来的
//	 */
//	public static final String PAGE = "page";
//	/**
//	 * 分页参数，每页数据条数，从前端传过来的
//	 */
//	public static final String ROWS = "rows";
//	/**
//	 * 分页参数，起始位置，从0开始
//	 */
//	public static final String START = "start";
//	/**
//	 * 分页参数，每页数据条数
//	 */
//	public static final String LENGTH = "length";
//	/**
//	 * 升序
//	 */
//	public static final String SORT_ASC = "asc";
//	/**
//	 * 降序
//	 */
//	public static final String SORT_DESC = "desc";
//	/**
//	 * 转换并校验分页参数<br>
//	 * mybatis中limit #{start, JdbcType=INTEGER}, #{length,
//	 * JdbcType=INTEGER}里的类型转换貌似失效<br>
//	 * 我们这里先把他转成Integer的类型
//	 *
//	 * @param params
//	 * @param required
//	 *            分页参数是否是必填
//	 */
//	public static void pageParamConver(Map<String, Object> params, boolean required) {
//		if (required) {// 分页参数必填时，校验参数
//			if (params == null || !params.containsKey(PAGE) || !params.containsKey(ROWS)) {
//				throw new IllegalArgumentException("请检查分页参数" + PAGE + "," + ROWS);
//			}
//		}
//
//		if (params == null) {
//			if (params.containsKey(ROWS)) {
//				Integer rows = getInteger(params, ROWS);
//				if (rows < 0) {
//					log.error("rows：{}，重置为{}", rows, DEFAULT_ROWS);
//					rows = DEFAULT_ROWS;
//				}
//				params.put(LENGTH, rows);
//			}
//
//			if (params.containsKey(PAGE)) {
//				Integer start = getInteger(params, LENGTH) * (getInteger(params, PAGE) - 1);
//				if (start < 0) {
//					log.error("start：{}，重置为{}", start, DEFAULT_START);
//					start = DEFAULT_START;
//				}
//				params.put(START, start);
//			}
//		}
//	}
//
//	/**
//	 * 获取页数
//	 * @param params
//	 * @param total
//	 * @return
//	 */
//	public static int getTotalPage(Map<String, Object> params, Long total) {
//		if (params == null || !params.containsKey(LENGTH)) {
//			return 0;
//		}
//		Integer length = getInteger(params, LENGTH);
//		int totalPage = 0;
//
//		if (total % length == 0) {
//			totalPage = (int) (total / length);
//		} else {
//			totalPage = (int)(total / length) + 1;
//		}
//		return totalPage;
//	}
//
//	/**
//	 * 获取页数
//	 * @param rows
//	 * @param total
//	 * @return
//	 */
//	public static Long getTotalPage(int rows, Long total) {
//		Long totalPage = 0L;
//
//		if (total % rows == 0) {
//			totalPage = total / rows;
//		} else {
//			totalPage = total / rows + 1;
//		}
//		return totalPage;
//	}
//
//	private static Integer getInteger(Map<String, Object> params, String key) {
//		Object st = params.get(key);
//		try {
//			return (Integer)st;
//		} catch (Exception e) {
//			log.error("params 获取key：{} 值：{} 强转成Integer失败", key, params.get(key));
//			return 0;
//		}
//	}
//
//}
