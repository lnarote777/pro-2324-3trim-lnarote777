package output

interface IOutputInfo {
    fun showMessage(mensaje: String, salto: Boolean = true)
}