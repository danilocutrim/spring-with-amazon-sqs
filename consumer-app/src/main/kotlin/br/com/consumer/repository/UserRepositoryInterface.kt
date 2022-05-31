package br.com.consumer.repository

import br.com.consumer.model.entity.User

interface UserRepositoryInterface<T : User> {
    fun save(entity: T): User
    fun get(document: String, id: String): T
}
