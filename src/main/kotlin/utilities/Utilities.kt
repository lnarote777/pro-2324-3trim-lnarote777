package utilities

import entity.CtfEntity
import output.IOutputInfo
import services.ICtfService
import services.IGroupService
import java.io.File

/**
 * A utility class that provides various commands for managing groups and CTF (Capture The Flag) entities.
*/
class Utilities(private val console: IOutputInfo, private val groupService: IGroupService, private val ctfService: ICtfService, private val fileReader: IFileReader) {

    /**
     * Processes the command-line arguments and executes the corresponding command.
     */
    fun checkCommands(args: Array<String>){
        if (args.isEmpty()) {
            console.showMessage("*** ERROR - No command provided ***")
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
            else -> console.showMessage("*** ERROR - Command $argumento invalid ***")
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
                    val ctfs = ctfService.getAllCtf()
                    val groupExist = ctfs?.find { groupId == it.groupId && ctfId == it.ctfId}
                    val ctf = CtfEntity(ctfId, groupId, puntuaction)
                    val group = groupService.getGroupById(groupId)

                    if (groupExist == ctf){
                        ctfService.updateCtf(ctf)
                        console.showMessage("*** Score Updated ***")
                        if (group != null) {
                            val (position, ctfId) = getBestPosition(groupId)
                            groupService.updateGroup(ctfId, group)
                        }
                    }else{
                        ctfService.createCtf(ctf)
                        if (group != null) {
                            val (position, ctfId) = getBestPosition(groupId)
                            if (ctfId == 0){
                                groupService.updateGroup(null, group)
                            }else{
                                groupService.updateGroup(ctfId, group)
                            }
                        }
                        console.showMessage("*** Participation created successfully ***")
                    }


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
                    ctfService.deleteCtfByGroupId(groupId)
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
                    val ctfs = ctfService.getAllCtf()

                    if(ctfs != null){
                        val ctf = ctfs.find { ctfid == it.ctfId }

                        if (ctf != null) {
                            val groups = groupService.getAllGroups()
                            val group = groups?.find { groupId == it.groupId }

                            if (group != null) {
                                groupService.updateGroup(null, group)
                                ctfService.deleteCtfByGroupId(groupId)

                                val (position, ctfId) = getBestPosition(groupId)
                                if (ctfId == 0){
                                    groupService.updateGroup(null, group)
                                }else{
                                    if(ctfs.find { ctfId == it.ctfId && groupId == it.groupId } == null){
                                        groupService.updateGroup(null, group)
                                    }else{
                                        groupService.updateGroup(ctfId, group)
                                    }
                                }
                                console.showMessage("*** Participation deleted successfully ***")
                            }
                        }else{
                            console.showMessage("*** CtfId not found ***")
                        }
                    }
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
                    val ctfs = ctfService.getAllCtf()
                    val ctfsGroup = ctfs?.filter { id == it.groupId }

                    if (group != null && ctfsGroup != null){
                        val ctfsGroupOrdered = ctfsGroup.sortedByDescending { it.punctuation }

                        val (position, ctfId) = getBestPosition(group.groupId)
                        val ctf = ctfService.getCtfById(ctfId)?.find { group.groupId == it.groupId }

                        console.showMessage("Processed: List of participation of the group '${group.groupDesc}'")
                        console.showMessage("GROUP: ${group.groupId}   ${group.groupDesc}  MEJORCTF: $ctfId, Position: $position , Score: ${ctf?.punctuation}")
                        console.showMessage("CTF   | Score | Position")
                        console.showMessage("------------------------")
                        ctfsGroupOrdered.forEach { ctf ->
                            val position = getPosition(group.groupId, ctf.ctfId)
                            console.showGroup(group, ctf, position)
                        }

                    }else{
                        console.showMessage("*** Group not found. ***")
                    }
                } else {
                console.showMessage("*** Invalid group ID ***")
                }
            }else if (args.size == 1){
                val groups = groupService.getAllGroups()
                console.showMessage("  GROUPID  |  GROUPDESC  |  BESTPOSCETFID")
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

                        val groupId = ctfOrder[0].groupId
                        val group = groupService.getGroupById(groupId)

                        console.showMessage("Processed: List of participation CTF: '$ctfId'")
                        console.showMessage("WINNER: ${group?.groupDesc} Best Score: ${ctfOrder[0].punctuation} Total players: ${ctfs.size}")

                        console.showMessage("GROUP   | Score")
                        console.showMessage("---------------")

                        ctfOrder.forEach { ctf ->
                            val groupId = ctf.groupId
                            val group = groupService.getGroupById(groupId)
                            if (group != null) {
                                console.showCtf(ctf, group)
                            }
                        }

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

    /**
    * Checks the given file and checks each argument found in it.
    */
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
                                checkCommands(args)
                            }else{
                                val args = arrayOf(pair.first, argument)
                                checkCommands(args)
                            }
                        }else if (pair.second == "-l"){
                            val args = arrayOf(pair.first)
                            checkCommands(args)

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

    private fun getBestPosition(groupId: Int): Pair<Int, Int>{
        var best: Pair<Int, Int>? = null
        var best2 = 0
        val allCtfs = ctfService.getAllCtf()
        val ctfsIds = mutableListOf<Int>()

        allCtfs?.forEach { ctf ->
            if (ctf.ctfId !in ctfsIds) ctfsIds.add(ctf.ctfId)
        }

        for (ctfId in ctfsIds){
            val ctfs = ctfService.getCtfById(ctfId)?.sortedByDescending { it.punctuation }
            var count = 0

            if (ctfs != null){
                while (count < ctfs.size && ctfs[count].groupId != groupId) {
                    count++
                }

                val ctf = ctfService.getCtfById(ctfId)?.find { groupId == it.groupId }
                if (best2 > count || ctf != null) {
                    best2 = count
                    best = Pair((count + 1), ctfId)
                }
            }else{
                console.showMessage("")
            }

        }
        return best?: Pair(0, 0)
    }

    private fun getPosition(groupId: Int, ctfId: Int): Int{

        val ctfs = ctfService.getCtfById(ctfId)
        var count = 0

        if (ctfs != null){
            ctfs.sortedByDescending { it.punctuation }

            while (ctfs[count].groupId != groupId){
                count++
            }

        }else{
            console.showMessage("")
        }
        return count + 1
    }

}