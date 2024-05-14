package dao

import entity.GroupEntity
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

class GroupDAOH2(private val dataSource: DataSource, private val console: IOutputInfo): IGroupDAO {
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