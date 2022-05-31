package br.com.producer.service

import br.com.producer.model.request.UserSaveRequest
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class UserService() {
    val logger = KotlinLogging.logger {}

    fun saveUser(
        userSaveRequest: UserSaveRequest,
        document: String
    ) {
        logger.info { "saveUser: saving user, document: $document " }
    }
}
