package dao

import entity.CtfEntity
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

class CtfDAOH2(private val dataSource: DataSource, private val console: IOutputInfo): ICtfDAO {
    override fun createCtf(ctf: CtfEntity): CtfEntity? {
        val sql = "INSERT INTO CTFS (CTFID, GRUPOID, PUNTUACION) VALUES (?, ?, ?)"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, ctf.ctfId)
                    stmt.setInt(2, ctf.groupId)
                    stmt.setInt(3, ctf.punctuation)
                    val rs = stmt.executeUpdate()
                    if (rs == 1){
                        ctf
                    }else{
                        console.showMessage("Error - Insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun getCtfById(id: Int): CtfEntity? {
        val sql = "SELECT * FROM CTFS WHERE CTFID = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        CtfEntity(
                            ctfId = rs.getInt("CTFID"),
                            groupId = rs.getInt("GRUPOID"),
                            punctuation = rs.getInt("PUNTUACION")
                        )
                    } else {
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun updateCtf(ctf: CtfEntity): CtfEntity? {
        val sql = "UPDATE CTFS SET GRUPOID = ?, PUNTUACION = ? WHERE CTFID = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, ctf.groupId)
                    stmt.setInt(2, ctf.punctuation)
                    stmt.setInt(3, ctf.ctfId)
                    val rs = stmt.executeUpdate()

                    if (rs == 1){
                        ctf
                    }else{
                        console.showMessage("Error - Update query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun deleteCtf(id: Int): Boolean? {
        val sql = "DELETE FROM CTFS WHERE CTFID = ?"

        return try{
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    (stmt.executeUpdate() == 1)
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

    override fun getAllCtf(): List<CtfEntity>? {
        val sql = "SELECT * FROM CTFS"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val ctfs = mutableListOf<CtfEntity>()
                    while (rs.next()) {
                        ctfs.add(
                            CtfEntity(
                                ctfId = rs.getInt("CTFID"),
                                groupId = rs.getInt("GRUPOID"),
                                punctuation = rs.getInt("PUNTUACION")
                            )
                        )
                    }
                    ctfs
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }
}