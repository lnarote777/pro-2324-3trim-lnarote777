package services

import entity.CtfEntity

interface ICtfService {
    fun createCtf(ctf: CtfEntity): CtfEntity?
    fun getCtfById(id: Int): List<CtfEntity>?
    fun updateCtf(ctf: CtfEntity): CtfEntity?
    fun deleteCtfByGroupId(id: Int): Boolean?
    fun getAllCtf(): List<CtfEntity>?
    fun deleteCtf(id: Int): Boolean?
}