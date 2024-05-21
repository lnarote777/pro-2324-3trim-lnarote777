import androidx.compose.ui.window.application
import dao.ctfs.CtfDAOH2
import dao.groups.GroupDAOH2
import factory.DAOFactory
import factory.DataSourceFactory

import output.Console
import services.CtfServiceImpl
import services.GroupServiceImpl
import utilities.FileReader
import utilities.Utilities
import java.io.File

fun main(args: Array<String>) = application {
    val console = Console()

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val fileReader = FileReader()
    val fileType = fileReader.readFileConfig("config.ini")

    val daoGroup = DAOFactory(dataSource, console).getGroupDao(DAOFactory.DaoSourceType.SQL)
    val daoCtf = DAOFactory(dataSource, console).getCtfDao(DAOFactory.DaoSourceType.SQL)

    val groupServiceImpl = GroupServiceImpl(daoGroup)
    val ctfServiceImpl = CtfServiceImpl(daoCtf)

    //val graphicInterface = GraphicInterface()

    val argus = arrayOf("-c", "1")
    Utilities(console, groupServiceImpl, ctfServiceImpl, fileReader ).comprobarArgumentos(argus)

}
