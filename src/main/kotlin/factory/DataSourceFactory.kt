package factory


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

/**
 * A factory object to create instances of [DataSource] for different data source types.
 * The factory supports HikariCP and JDBC data source configurations.
 */
object DataSourceFactory {
    enum class DataSourceType {
        HIKARI,
        JDBC
    }

    fun getDS(dataSourceType: DataSourceType): DataSource {
        return when (dataSourceType) {
            DataSourceType.HIKARI -> {
                val config = HikariConfig()
                config.jdbcUrl = "jdbc:h2:./default"
                config.username = "user"
                config.password = "user"
                config.driverClassName = "org.h2.Driver"
                config.maximumPoolSize = 10
                config.isAutoCommit = true
                config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                HikariDataSource(config)
            }

            DataSourceType.JDBC -> TODO()
        }
    }
}