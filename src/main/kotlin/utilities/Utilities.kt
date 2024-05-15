package utilities

import entity.CtfEntity
import entity.GroupEntity
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
            "-p" -> newParticipation(args)
            "-t" -> deleteGroup(args)
            "-e" -> deleteParticipation(args)
            "-l" -> showGroups(args, groupService)
            "-c" -> showCTF(args)
            "-f" -> comprobarArgF(args)
            "-i" -> showInterface()
            else -> console.showMessage("ERROR - Command $argumento invalid")
        }

    }

    private fun newGroup(args: Array<String>, groupService: IGroupService){

        if (args[1] != null){

        }
    }

    private fun newParticipation(args: Array<String>){

    }

    private fun deleteGroup(args: Array<String>){

    }

    private fun deleteParticipation(args: Array<String>){

    }

    private fun showGroups(args: Array<String>, groupService: IGroupService){

        val groups = groupService.getAllGroups()

        groups?.forEach { console.showGroups(it) }


    }

    private fun showCTF(args: Array<String>){

    }

    private fun comprobarArgF(args: Array<String>){

    }

    private fun showInterface(){

    }

}