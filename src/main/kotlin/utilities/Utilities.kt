package utilities

import entity.CtfEntity
import output.IOutputInfo
import services.ICtfService
import services.IGroupService


class Utilities(private val console: IOutputInfo) {

    fun comprobarArgumentos(args: Array<String>, groupService: IGroupService, ctfService: ICtfService, ){
        if (args.isEmpty()) {
            console.showMessage("ERROR - No command provided")
            return
        }

        val argumento = args[0]

        when (argumento){
            "-g" -> newGroup(args,groupService)
            "-p" -> newParticipation(args, ctfService)
            "-t" -> deleteGroup(args, groupService)
            "-e" -> deleteParticipation(args, ctfService)
            "-l" -> showGroups(args, groupService)
            "-c" -> showCTF(args, ctfService)
            "-f" -> ficheroComandos(args)
            "-i" -> showInterface()
            else -> console.showMessage("ERROR - Command $argumento invalid")
        }

    }

    private fun newGroup(args: Array<String>, groupService: IGroupService){
        try {
            if (args.size == 2){
                val groupDesc = args[1]
                groupService.createGroup(groupDesc)

            }else if(args.size < 2){
                console.showMessage("***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun newParticipation(args: Array<String>, ctfService: ICtfService){
        try {
            if(args.size == 4){
                val ctfId = args[1].toIntOrNull()
                val groupId = args[2].toIntOrNull()
                val puntuaction = args[3].toIntOrNull()

                if (ctfId != null && groupId != null && puntuaction != null){

                    val ctf = CtfEntity(ctfId, groupId, puntuaction)
                    ctfService.createCtf(ctf)
                }else{
                    console.showMessage("***")
                }

            }else if (args.size < 4){
                console.showMessage("***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun deleteGroup(args: Array<String>, groupService: IGroupService){
        try {
            if (args.size == 2){
                val groupId = args[1].toIntOrNull()

                if (groupId != null) {
                    groupService.deleteGroup(groupId)
                    console.showMessage("*** Deleted Successful ***")
                }else{
                    console.showMessage("*** Command argument invalid ***")
                }

            }else if(args.size < 2){
                console.showMessage("***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun deleteParticipation(args: Array<String>, ctfService: ICtfService){
        try {
            if (args.size == 3){

            }else if(args.size < 3){
                console.showMessage("***")
            }else{
                console.showMessage("*** Too many arguments for command ${args[0]} ***")
            }
        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun showGroups(args: Array<String>, groupService: IGroupService){

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

    private fun showCTF(args: Array<String>, ctfService: ICtfService){
        try {

        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun ficheroComandos(args: Array<String>){
        try {

        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

    private fun showInterface(){
        try {

        }catch (e: Exception){
            console.showMessage("ERROR - ${e.message}")
        }
    }

}