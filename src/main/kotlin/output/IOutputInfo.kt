package output

import entity.GroupEntity

interface IOutputInfo {
    fun showMessage(mensaje: String, salto: Boolean = true)
    fun showGroups(group: GroupEntity)
}