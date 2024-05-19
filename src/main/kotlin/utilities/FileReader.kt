package utilities

import java.io.File

class FileReader {
    fun readFileCommand(file: File): MutableList<Pair<String, String>> {
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

//    fun readFileConfig(path: String): String {
//        val file = File(path)
//        var partes = listOf<String>()
//
//        file.forEachLine { line ->
//            if (line.startsWith("tipo")){
//                partes = line.split("=")
//            }
//        }
//        return partes[3]
//    }
}