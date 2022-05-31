package br.com.consumer.model.request

import br.com.consumer.model.interfaces.User
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserSaveRequest(
    val nickName: String,
    val lastName: String,
    override val country: String,
    override val name: String,
    override val birthDate: String
) : User
