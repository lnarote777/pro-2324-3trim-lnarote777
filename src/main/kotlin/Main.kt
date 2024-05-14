
import dao.GroupDAOH2
import factory.DataSourceFactory
import output.Console

fun main() {
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)
    val console = Console()

    val grupos = GroupDAOH2(dataSource, console).getAllGroups()

    grupos?.forEach { println(it) }
}
