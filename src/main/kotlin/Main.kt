
import dao.CtfDAOH2
import dao.GroupDAOH2
import factory.DataSourceFactory
import output.Console
import services.CtfServiceImpl
import services.GroupServiceImpl
import utilities.Utilities

fun main(args: Array<String>) {

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)
    val console = Console()

    val groupDAOH2 = GroupDAOH2(dataSource, console)
    val ctfsDAOH2 = CtfDAOH2(dataSource, console)

    val groupServiceImpl = GroupServiceImpl(groupDAOH2)
    val ctfServiceImpl = CtfServiceImpl(ctfsDAOH2)


    Utilities(console).comprobarArgumentos(args, groupServiceImpl, ctfServiceImpl)

}
