package utilities

import entity.CtfEntity
import output.IOutputInfo
import services.ICtfService
import services.IGroupService
import java.io.File


class Utilities(private val console: IOutputInfo, private val groupService: IGroupService, private val ctfService: ICtfService, private val fileReader: FileReader) {

    fun comprobarArgumentos(args: Array<String>){
        if (args.isEmpty()) {
            console.showMessage("ERROR - No command provided")
            return
        }

        val argumento = args[0]

        when (argumento){
            "-g" -> newGroup(args)
            "-p" -> newParticipation(args)
            "-t" -> deleteGroup(args)
            "-e" -> deleteParticipation(args)
            "-l" -> showGroups(args)
            "-c" -> showCTF(args)
            "-f" -> commandFile(args)
            "-i" -> showInterface()
            else -> console.showMessage("ERROR - Command $argumento invalid")
        }

    }

    private fun newGroup(args: Array<String>){
        try {
            if (args.size == 2){
                val groupDesc = args[1]
                groupService.createGroup(groupDesc)
                console.showMessage("*** Group created successfully ***")
            }else if(args.size < 2){
                console.showMessage("*** Missing argument for command ${args[0]} ***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun newParticipation(args: Array<String>){
        try {
            if(args.size == 4){
                val ctfId = args[1].toIntOrNull()
                val groupId = args[2].toIntOrNull()
                val puntuaction = args[3].toIntOrNull()

                if (ctfId != null && groupId != null && puntuaction != null){

                    val ctf = CtfEntity(ctfId, groupId, puntuaction)
                    ctfService.createCtf(ctf)
                    console.showMessage("*** Participation created successfully ***")
                }else{
                    console.showMessage("*** Invalid arguments for command ${args[0]} ***")
                }

            }else if (args.size < 4){
                console.showMessage("*** Missing arguments for command ${args[0]} ***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun deleteGroup(args: Array<String>){
        try {
            if (args.size == 2){
                val groupId = args[1].toIntOrNull()

                if (groupId != null) {
                    groupService.deleteGroup(groupId)
                    console.showMessage("*** Group deleted successfully ***")
                }else{
                    console.showMessage("*** Invalid group ID for command ${args[0]} ***")
                }

            }else if(args.size < 2){
                console.showMessage("*** Missing argument for command ${args[0]} ***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun deleteParticipation(args: Array<String>){
        try {
            if (args.size == 3){
                val ctfid = args[1].toIntOrNull()
                val groupId = args[2].toIntOrNull()

                if (ctfid != null && groupId != null){
                    TODO()
                    console.showMessage("*** Participation deleted successfully ***")
                } else {
                    console.showMessage("*** Invalid arguments for command ${args[0]} ***")
                }

            }else if(args.size < 3){
                console.showMessage("*** Missing argument for command ${args[0]} ***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun showGroups(args: Array<String>){

        try {
            if(args.size == 2){
                val id = args[1].toIntOrNull()

                if (id != null){
                    val group = groupService.getGroupById(id)
                    if (group != null){
                        console.showGroups(group)
                    }else{
                        console.showMessage("*** Group not found. ***")
                    }
                } else {
                console.showMessage("*** Invalid group ID ***")
                }
            }else if (args.size == 1){
                val groups = groupService.getAllGroups()
                groups?.forEach { console.showGroups(it) }

            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }

        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }

    }

    private fun showCTF(args: Array<String>){
        try {
            if (args.size == 2){
                val ctfId = args[1].toIntOrNull()

                if (ctfId != null){
                    val ctfs = ctfService.getCtfById(ctfId)

                    if (ctfs != null) {
                        val ctfOrder = ctfs.sortedByDescending { it.punctuation }
                        ctfOrder.forEach { console.showCtfs(it) }
                    }else{
                        console.showMessage("*** CTF not found. ***")
                    }
                }else{
                    console.showMessage("*** Invalid CTF ID ***")
                }
            }else if (args.size < 2){
                console.showMessage("*** Missing argument for command ${args[0]} ***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]}")
            }

        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun commandFile(args: Array<String>){
        try {
            if (args.size == 2){
                val path = args[1]
                val file = File(path)

                if(file.exists()){
                    val commands = fileReader.readFileCommand(file)
                    commands.forEach { pair->
                        if (pair.first != pair.second){
                            val argument = pair.second

                            if(argument.contains(";")){
                                val arguments = pair.second.split(";")
                                val args = arrayOf(pair.first, arguments[0], arguments[1], arguments[2])
                                comprobarArgumentos(args)
                            }else{
                                val args = arrayOf(pair.first, argument)
                                comprobarArgumentos(args)
                            }
                        }else if (pair.second == "-l"){
                            val args = arrayOf(pair.first)
                            comprobarArgumentos(args)

                        }
                    }
                }else{
                    console.showMessage("*** The file doesn't exist. ***")
                }

            }else if(args.size < 2){
                console.showMessage("*** Missing argument for command ${args[0]} ***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun showInterface(){
        try {
            val ui = true

        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

}