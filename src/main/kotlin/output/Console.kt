package output

import entity.GroupEntity

class Console: IOutputInfo {
    override fun showMessage(mensaje: String, salto: Boolean) {
        if (salto) println(mensaje) else print(mensaje)
    }

    override fun showGroups(group: GroupEntity) {
        println(group)
    }
}