package chapter7

import org.springframework.jdbc.core.JdbcTemplate
import org.h2.jdbcx.*

fun initJdbcTemplate(): JdbcTemplate {
    return JdbcTemplate(JdbcDataSource()
        .apply {
            setUrl("jdbc:h2:mem:facts_app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false")
        })
        .apply {
            execute("CREATE TABLE USERS (ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "FIRST_NAME VARCHAR(64) NOT NULL, LAST_NAME VARCHAR(64) NOT NULL, " +
                    "GENDER VARCHAR(8) NOT NULL);")
            execute("CREATE TABLE USERS (ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "VALUE_ TEXT NO NULL, USER INT NOT NULL, FOREIGN KEY (USER) REFERENCES " +
                    "USERS(ID) ON DELETE RESTRICT")
        }
}