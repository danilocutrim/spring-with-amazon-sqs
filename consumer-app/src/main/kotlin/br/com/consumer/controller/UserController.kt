package br.com.consumer.controller

import br.com.consumer.model.entity.User
import br.com.consumer.service.UserService
import javax.validation.constraints.NotBlank
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val userService: UserService
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun getUser(
        @RequestParam("id") @NotBlank id: String,
        @NotBlank @RequestHeader("document") document: String
    ): User {
        logger.info { "getUser: finding user by document: $document Id: $id" }
        return userService.getUser(
            document = document,
            id = id,
        ).also {
            logger.info {
                "getUser: find successfully to " +
                        "document: $document Id: $id"
            }
        }
    }
}
