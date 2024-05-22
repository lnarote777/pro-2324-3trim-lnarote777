package output

import entity.CtfEntity
import entity.GroupEntity

/**
 * A class that implements [IOutputInfo] to display messages and data to the console.
 */
class Console: IOutputInfo {
    override fun showMessage(mensaje: String, salto: Boolean) {
        if (salto) println(mensaje) else print(mensaje)
    }

    override fun showGroup(group: GroupEntity, ctf: CtfEntity) {
        showMessage("  ${ctf.ctfId}   |   ${ctf.punctuation}  |   ${group.bestPosCtfId}")
    }

    override fun showGroups(group: GroupEntity) {
        showMessage("${group.groupId} | ${group.groupDesc} | ${group.bestPosCtfId}")
    }

    override fun showCtf(ctf: CtfEntity, group: GroupEntity){
        showMessage("${group.groupDesc} | ${ctf.punctuation}")
    }
}