///*
// * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package test.tonels;
//
//import com.querydsl.core.types.Expression;
//import com.querydsl.jpa.HQLTemplates;
//import com.querydsl.jpa.JPQLSerializer;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public abstract class AbstractQueryTest {
//
//    protected QueryHelper<?> query() {
//        return new QueryHelper<Void>(HQLTemplates.DEFAULT);
//    }
//
//    protected static void assertToString(String expected, Expression<?> expr) {
//        JPQLSerializer serializer = new JPQLSerializer(HQLTemplates.DEFAULT, null);
//        assertEquals(expected, serializer.handle(expr).toString().replace("\n", " "));
//    }
//
//    protected static void assertMatches(String expected, Expression<?> expr) {
//        JPQLSerializer serializer = new JPQLSerializer(HQLTemplates.DEFAULT, null);
//        String str = serializer.handle(expr).toString().replace("\n", " ");
//        assertTrue(expected + "\n!=\n" + str, str.matches(expected));
//    }
//
//}
