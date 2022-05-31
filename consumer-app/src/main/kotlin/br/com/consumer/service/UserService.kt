package br.com.consumer.service

import br.com.consumer.model.entity.User
import br.com.consumer.model.request.UserSaveRequest
import br.com.consumer.repository.UserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository
) {
    val logger = KotlinLogging.logger {}

    fun saveUser(
        userSaveRequest: UserSaveRequest,
        document: String
    ): User {
        logger.info { "saveUser: saving user, document: $document " }
        val response =
            repository.save(
                User(
                    document = document,
                    lastName = userSaveRequest.lastName,
                    country = userSaveRequest.country,
                    nickName = userSaveRequest.nickName,
                    birthDate = userSaveRequest.birthDate,
                    name = userSaveRequest.name
                )
            )
        return response.also {
            logger.info {
                "saveUser: saved user" +
                        "document: $document" +
                        "Id: ${response.userId}"
            }
        }
    }

    fun getUser(id: String, document: String): User {

        logger.info { "getUser: finding user,document: $document, Id: $id" }
        return repository.get(document = document, id = id)
            .also {
                logger.info {
                    "getUser: find successfully for ," +
                            "document: $document, Id: $id"
                }
            }
    }

}
