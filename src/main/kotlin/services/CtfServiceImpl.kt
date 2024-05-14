package services

import dao.CtfDAOH2
import entity.CtfEntity

class CtfServiceImpl(private val ctfDAO: CtfDAOH2): ICtfService {
    override fun createCtf(ctf: CtfEntity): CtfEntity? {
        return ctfDAO.createCtf(ctf)
    }

    override fun getCtfById(id: Int): CtfEntity? {
        return ctfDAO.getCtfById(id)
    }

    override fun updateCtf(ctf: CtfEntity): CtfEntity? {
        return ctfDAO.updateCtf(ctf)
    }

    override fun deleteCtf(id: Int): Boolean? {
        return ctfDAO.deleteCtf(id)
    }

    override fun getAllCtf(): List<CtfEntity>? {
        return ctfDAO.getAllCtf()
    }
}