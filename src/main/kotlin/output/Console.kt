package output

import entity.CtfEntity
import entity.GroupEntity

class Console: IOutputInfo {
    override fun showMessage(mensaje: String, salto: Boolean) {
        if (salto) println(mensaje) else print(mensaje)
    }

    override fun showGroups(group: GroupEntity) {
        println(group)
    }

    override fun showCtfs(ctf: CtfEntity){
        println(ctf)
    }
}