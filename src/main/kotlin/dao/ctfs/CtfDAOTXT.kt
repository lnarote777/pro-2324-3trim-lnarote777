package dao.ctfs

import entity.CtfEntity
import output.IOutputInfo
import javax.sql.DataSource

class CtfDAOTXT(private val dataSource: DataSource, private val console: IOutputInfo): ICtfDAO {
    override fun createCtf(ctf: CtfEntity): CtfEntity? {
        TODO("Not yet implemented")
    }

    override fun getCtfById(id: Int): List<CtfEntity>? {
        TODO("Not yet implemented")
    }

    override fun updateCtf(ctf: CtfEntity): CtfEntity? {
        TODO("Not yet implemented")
    }

    override fun deleteCtf(id: Int): Boolean? {
        TODO("Not yet implemented")
    }

    override fun getAllCtf(): List<CtfEntity>? {
        TODO("Not yet implemented")
    }
}