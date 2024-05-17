package dao.ctfs

import entity.CtfEntity
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

/**
 * Implementation of [ICtfDAO] for managing CTF (Capture The Flag) entities in an H2 database.
 *
 * @property dataSource The [DataSource] for database connections.
 * @property logger The logger for error logging.
 */
class CtfDAOH2(private val dataSource: DataSource, private val console: IOutputInfo): ICtfDAO {

    /**
     * Inserts a new CTF entity into the database.
     *
     * @param ctf The CTF entity to be created.
     * @return The created CTF entity if successful, null otherwise.
     */
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

    /**
     * Retrieves a CTF entity by its ID from the database.
     *
     * @param id The ID of the CTF entity to retrieve.
     * @return The retrieved CTF entity if found, null otherwise.
     */
    override fun getCtfById(id: Int): List<CtfEntity>? {
        val sql = "SELECT * FROM CTFS WHERE CTFID = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
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

    /**
     * Updates an existing CTF entity in the database.
     *
     * @param ctf The CTF entity to be updated.
     * @return The updated CTF entity if successful, null otherwise.
     */
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

    /**
     * Deletes a CTF entity from the database by its ID.
     *
     * @param id The ID of the CTF entity to delete.
     * @throws RuntimeException if the deletion fails.
     */
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

    /**
     * Retrieves all CTF entities from the database.
     *
     * @return A list of all CTF entities if successful, null otherwise.
     */

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