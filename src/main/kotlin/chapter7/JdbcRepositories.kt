package chapter7

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class JdbcUserRepository(template: JdbcTemplate): JdbcRepository(template), UserRepository {
    override fun getUserById(id: UserId): User? {
        return toNullable {
            template.queryForObject("select * from USERS whre id = ?", id) {
                resultSet, _ ->
                    with(resultSet) {
                        User(getInt("ID"),
                        getString("FIRST_NAME"),
                        getString("LAST_NAME"),
                        Gender.valueOfIgnoreCase(getString("GENDER")))
                    }
            }
        }
    }

    override fun insertUser(user: User) {
        template.update("INSERT INTO USERS VALUES (?,?,?,?)",
            user.id,
            user.firstName,
            user.lastName,
            user.gender.name)
    }
}

class JdbcFactRepository(template: JdbcTemplate): JdbcRepository(template), FactRepository {
    override fun getFactByUserId(id: UserId): Fact? {
        return toNullable {
            template.queryForObject("select * from USERS as U inner join FACTS as F on U.ID where U.ID=?", id) {
                resultSet, _ ->
                    with(resultSet) {
                        Fact(getInt(5),
                        getString(6),
                        User(getInt(1),
                        getString(2),
                        getString(3),
                        Gender.valueOfIgnoreCase(getString(4))))
                    }
            }
        }
    }

    override fun insertFact(fact: Fact) {
        template.update("INSERT INTO FACTS VALUES (?,?,?)", fact.id, fact.value, fact.user?.id)
    }
}