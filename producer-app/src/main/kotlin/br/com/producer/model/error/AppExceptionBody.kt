package br.com.producer.model.error

import java.time.LocalDateTime

class AppExceptionBody(
    val timeStamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)
