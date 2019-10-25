package com.tonels.modelTable.dao;

import com.tonels.modelTable.model.S1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StuDao {

    //    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
    private final static String findById = "SELECT * FROM s1 where s1.s_id = ? ";
    private final static String findALL = "SELECT * FROM s1";

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public S1 jdbc1(Integer id) {
        final S1 s1 = new S1();
        jdbcTemplate.query(findById, new Object[]{id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                s1.setSId(resultSet.getInt("s_id"));
                s1.setVersion(resultSet.getInt("version"));
                Date s_birthday = resultSet.getDate("s_birthday");
                LocalDate parse = LocalDate.parse(s_birthday.toString());

                s1.setSBirthday(parse);
                s1.setSName(resultSet.getString("s_name"));
                s1.setSex(resultSet.getString("sex"));
            }
        });
        return s1;
    }

    public List<S1> jdbc2() {
        final ArrayList<S1> s1s = new ArrayList<S1>();
        jdbcTemplate.query(findALL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    S1 s1 = new S1();
                    s1.setSId(resultSet.getInt("s_id"));
                    s1.setVersion(resultSet.getInt("version"));
                    Date s_birthday = resultSet.getDate("s_birthday");
                    LocalDate parse = LocalDate.parse(s_birthday.toString());

                    s1.setSBirthday(parse);
                    s1.setSName(resultSet.getString("s_name"));
                    s1.setSex(resultSet.getString("sex"));
                    s1s.add(s1);
                }

            }
        });
        return s1s;
    }


}
