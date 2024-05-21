package utilities

import factory.DAOFactory
import java.io.File

interface IFileReader {
    fun readFileCommand(file: File): MutableList<Pair<String, String>>
    fun readFileConfig(path: String): DAOFactory.DaoSourceType?
}
