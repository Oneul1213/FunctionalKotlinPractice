package chapter7

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate

abstract class JdbcRepository(protected val template: JdbcTemplate) {
    protected fun <T> toNullable(block: () -> T): T? {
        return try {
            block()
        } catch (_: EmptyResultDataAccessException) {
            null
        }
    }
}