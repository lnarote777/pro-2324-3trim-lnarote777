import androidx.compose.ui.window.application
import factory.DAOFactory
import factory.DataSourceFactory

import output.Console
import services.CtfServiceImpl
import services.GroupServiceImpl
import utilities.FileReader
import utilities.Utilities

fun main(args: Array<String>) = application {
    val console = Console()

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val fileReader = FileReader()
    val fileType = fileReader.readFileConfig("un9pe.ini")

    val daoGroup = DAOFactory(dataSource, console).getGroupDao(fileType)
    val daoCtf = DAOFactory(dataSource, console).getCtfDao(fileType)

    val groupServiceImpl = GroupServiceImpl(daoGroup)
    val ctfServiceImpl = CtfServiceImpl(daoCtf)

    //val graphicInterface = GraphicInterface()

    val argus = arrayOf("-l", "2")
    Utilities(console, groupServiceImpl, ctfServiceImpl, fileReader ).checkCommands(argus)

}
