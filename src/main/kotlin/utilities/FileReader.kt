package utilities

import factory.DAOFactory
import factory.DataSourceFactory
import java.io.File

/**
 * An implementation of the IFileReader interface for reading commands and configurations from files.
 */
class FileReader: IFileReader {

    /**
     * Reads commands from the given file and returns a list of command pairs.
     * Each pair contains a command and its associated line.
     */
    override fun readFileCommand(file: File): MutableList<Pair<String, String>> {
        val commands = mutableListOf<Pair<String, String>>()
        var command = ""

        file.forEachLine { line ->
            val trimmedLine = line.trim()
            if (trimmedLine.isNotEmpty() && !trimmedLine.startsWith("#")) {

                    if (trimmedLine.startsWith("-")) {
                        command = trimmedLine
                    }


                    val pair = Pair(command, trimmedLine)
                    commands.add(pair)

            }
        }

        return commands
    }

    /**
     * Reads a configuration file to determine the type of DAO source.
     */
   override fun readFileConfig(path: String): DAOFactory.DaoSourceType {
        val file = File(path)
        var partes = listOf<String>()

        file.forEachLine { line ->
            if (line.startsWith("tipo")){
                partes = line.split("=")
            }
        }
        return when (partes[1]){
            "SQL" -> DAOFactory.DaoSourceType.SQL
            "JSON" -> DAOFactory.DaoSourceType.JSON
            "TXT" -> DAOFactory.DaoSourceType.TXT
            "XML" -> DAOFactory.DaoSourceType.XML
            else -> TODO()
        }
   }
}