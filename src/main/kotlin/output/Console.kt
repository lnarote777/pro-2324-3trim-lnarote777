package output

import entity.CtfEntity
import entity.GroupEntity

class Console: IOutputInfo {
    override fun showMessage(mensaje: String, salto: Boolean) {
        if (salto) println(mensaje) else print(mensaje)
    }

    override fun showGroups(group: GroupEntity) {
        showMessage("     ${group.groupId}     |   ${group.groupDesc}   |    ${group.bestPosCtfId}")
    }

    override fun showCtfs(ctf: CtfEntity){
        showMessage("     ${ctf.ctfId}   |     ${ctf.groupId}     |    ${ctf.punctuation}")
    }
}