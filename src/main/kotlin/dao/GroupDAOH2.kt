package dao

import entity.GroupEntity
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

/**
 * Implementation of [IGroupDAO] for managing group entities in an H2 database.
 *
 * @property dataSource The [DataSource] for database connections.
 * @property console The console output handler.
 */
class GroupDAOH2(private val dataSource: DataSource, private val console: IOutputInfo): IGroupDAO {

    /**
     * Inserts a new group entity into the database.
     *
     * @param group The group entity to be created.
     * @return The created group entity if successful, null otherwise.
     */
    override fun createGroup(group: GroupEntity): GroupEntity? {
        val sql = "INSERT INTO GRUPOS (GRUPOID, GRUPODESC, MEJORPOSCTFID) VALUES (?, ?, ?)"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, group.groupId)
                    stmt.setString(2, group.groupDesc)
                    stmt.setInt(3, group.bestPosCtfId)
                    val rs = stmt.executeUpdate()
                    if (rs == 1){
                        group
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
     * Retrieves a group entity by its ID from the database.
     *
     * @param id The ID of the group entity to retrieve.
     * @return The retrieved group entity if found, null otherwise.
     */
    override fun getGroupById(id: Int): GroupEntity? {
        val sql = "SELECT * FROM GRUPOS WHERE GRUPOID = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        GroupEntity(
                            groupId = rs.getInt("GRUPOID"),
                            groupDesc = rs.getString("GRUPODESC"),
                            bestPosCtfId = rs.getInt("MEJORPOSCTFID")
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

    /**
     * Updates an existing group entity in the database.
     *
     * @param group The group entity to be updated.
     * @return The updated group entity if successful, null otherwise.
     */
    override fun updateGroup(group: GroupEntity): GroupEntity? {
        val sql = "UPDATE GRUPOS SET GRUPODESC = ?, MEJORPOSCTFID = ? WHERE GROUPID = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, group.groupDesc)
                    stmt.setInt(2, group.bestPosCtfId)
                    stmt.setInt(3, group.groupId)
                    val rs = stmt.executeUpdate()

                    if (rs == 1){
                        group
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
     * Deletes a group entity from the database by its ID.
     *
     * @param id The ID of the group entity to delete.
     * @return true if the deletion is successful, false otherwise.
     */
    override fun deleteGroup(id: Int): Boolean? {
        val sql = "DELETE FROM GRUPOS WHERE GRUPOID = ?"

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
     * Retrieves all group entities from the database.
     *
     * @return A list of all group entities if successful, null otherwise.
     */
    override fun getAllGroups(): List<GroupEntity>? {
        val sql = "SELECT * FROM GRUPOS"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val groups = mutableListOf<GroupEntity>()
                    while (rs.next()) {
                        groups.add(
                            GroupEntity(
                                groupId = rs.getInt("GRUPOID"),
                                groupDesc = rs.getString("GRUPODESC"),
                                bestPosCtfId = rs.getInt("MEJORPOSCTFID")
                            )
                        )
                    }
                    groups
                }
            }
        } catch (e: SQLException) {
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }
}