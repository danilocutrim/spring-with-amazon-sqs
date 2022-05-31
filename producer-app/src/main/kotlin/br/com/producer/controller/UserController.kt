package br.com.producer.controller

import br.com.producer.model.request.UserSaveRequest
import br.com.producer.service.UserService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val userService: UserService
) {
    private val logger = KotlinLogging.logger {}

    @PostMapping
    fun saveUser(
        @RequestBody userSaveRequest: UserSaveRequest,
        @RequestHeader("document") document: String
    ) {
        logger.info { "saveUser: saving User " }
        val response = userService.saveUser(
            userSaveRequest = userSaveRequest,
            document = document
        )
    }
}
