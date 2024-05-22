package services

import dao.ctfs.ICtfDAO
import entity.CtfEntity

/**
 * Implementation of [ICtfService] that uses a data access object (DAO) to perform CRUD operations on CTF entities.
 */
class CtfServiceImpl(private val ctfDAO: ICtfDAO): ICtfService {
    override fun createCtf(ctf: CtfEntity): CtfEntity? {
        return ctfDAO.createCtf(ctf)
    }

    override fun getCtfById(id: Int): List<CtfEntity>? {
        return ctfDAO.getCtfById(id)
    }

    override fun updateCtf(ctf: CtfEntity): CtfEntity? {
        return ctfDAO.updateCtf(ctf)
    }

    override fun deleteCtfByGroupId(id: Int): Boolean? {
        return ctfDAO.deleteCtfByGroupId(id)
    }

    override fun getAllCtf(): List<CtfEntity>? {
        return ctfDAO.getAllCtf()
    }

    override fun deleteCtf(id: Int): Boolean? {
        return ctfDAO.deleteCtf(id)
    }
}