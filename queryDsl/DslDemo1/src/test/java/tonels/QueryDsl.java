//package tonels;
//
//import com.querydsl.apt.Configuration;
//import com.querydsl.core.types.Expression;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class QueryDsl {
//    private static final Configuration configuration = new Configuration(SQLTemplates.DEFAULT);
//
//    private static String toString(Expression<?> e) {
//        return new SQLSerializer(configuration).handle(e).toString();
//    }
//
////    ROW_NUMBER() OVER (ORDER BY OrderDate) AS 'RowNumber'
//
////    ROW_NUMBER() OVER (PARTITION BY PostalCode ORDER BY SalesYTD DESC)
//
//    @Test
//    public void mutable() {
//        WindowFunction<Long> rn = rowNumber().over().orderBy(employee.firstname);
//        assertEquals("row_number() over (order by e.FIRSTNAME asc)", toString(rn));
//        assertEquals("row_number() over (order by e.FIRSTNAME asc, e.LASTNAME asc)", toString(rn.orderBy(employee.lastname)));
//    }
//
//    @Test
//    public void orderBy() {
//        assertEquals("row_number() over (order by e.FIRSTNAME asc)",
//                toString(rowNumber().over().orderBy(employee.firstname.asc())));
//
//        assertEquals("row_number() over (order by e.FIRSTNAME asc)",
//                toString(rowNumber().over().orderBy(employee.firstname)));
//
//        assertEquals("row_number() over (order by e.FIRSTNAME asc) as rn",
//                toString(rowNumber().over().orderBy(employee.firstname.asc()).as("rn")));
//
//        assertEquals("row_number() over (order by e.FIRSTNAME desc)",
//                toString(rowNumber().over().orderBy(employee.firstname.desc())));
//    }
//
//    @Test
//    public void partitionBy() {
//        assertEquals("row_number() over (partition by e.LASTNAME order by e.FIRSTNAME asc)",
//                toString(rowNumber().over().partitionBy(employee.lastname).orderBy(employee.firstname.asc())));
//
//        assertEquals("row_number() over (partition by e.LASTNAME, e.FIRSTNAME order by e.FIRSTNAME asc)",
//                toString(rowNumber().over().partitionBy(employee.lastname, employee.firstname).orderBy(employee.firstname.asc())));
//    }
//}
