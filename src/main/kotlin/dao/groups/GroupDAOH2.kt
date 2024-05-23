package dao.groups

import entity.GroupEntity
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

/**
 * Implementation of [IGroupDAO] for managing Group entities in an H2 database.
*/
class GroupDAOH2(private val dataSource: DataSource, private val console: IOutputInfo): IGroupDAO {

    override fun createGroup(groupDesc: String): String? {
        val sql = "INSERT INTO GRUPOS (GRUPODESC) VALUES (?)"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, groupDesc)
                    val rs = stmt.executeUpdate()
                    if (rs == 1){
                        groupDesc
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

    override fun updateGroup(ctfId: Int?, group: GroupEntity): GroupEntity? {
        val sql = "UPDATE GRUPOS SET MEJORPOSCTFID = ? WHERE GRUPOID = ?"

        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setObject(1, ctfId)
                    stmt.setInt(2, group.groupId)

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

    override fun deleteGroup(id: Int): Boolean? {
        val sql = "DELETE FROM GRUPOS WHERE GRUPOID = ?"

        return try{
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    val rs = stmt.executeUpdate()
                    if (rs == 1){
                        true
                    }else{
                        console.showMessage("Error - Update query failed! ($rs records inserted)")
                        false
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("Error - Insert query failed! (${e.message})")
            null
        }
    }

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