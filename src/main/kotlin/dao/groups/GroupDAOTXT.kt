package dao.groups

import dao.ctfs.ICtfDAO
import entity.GroupEntity
import output.IOutputInfo
import javax.sql.DataSource

/**
 * Implementation of [IGroupDAO] for managing Group entities in a TXT-based data source.
 */
class GroupDAOTXT(private val dataSource: DataSource, private val console: IOutputInfo): IGroupDAO {
    override fun createGroup(groupDesc: String): String? {
        TODO("Not yet implemented")
    }

    override fun getGroupById(id: Int): GroupEntity? {
        TODO("Not yet implemented")
    }

    override fun deleteGroup(id: Int): Boolean? {
        TODO("Not yet implemented")
    }

    override fun getAllGroups(): List<GroupEntity>? {
        TODO("Not yet implemented")
    }

    override fun updateGroup(ctfId: Int?, group: GroupEntity): GroupEntity? {
        TODO("Not yet implemented")
    }
}