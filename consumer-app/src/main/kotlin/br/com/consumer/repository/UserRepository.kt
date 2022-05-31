package br.com.consumer.repository

import br.com.consumer.constants.USER_NOT_FOUND
import br.com.consumer.exception.NotFoundException
import br.com.consumer.model.entity.User
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import mu.KotlinLogging
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val mapper: DynamoDBMapper, private val dynamoDb: DynamoDB) :
    UserRepositoryInterface<User> {

    val logger = KotlinLogging.logger { }

    override fun save(entity: User): User {
        logger.debug { "save: Saving user, document: ${entity.document}" }

        mapper.save(entity)
        return entity.also {
            logger.debug { "save: Save user, document: ${entity.document}" }
        }
    }

    override fun get(document: String, id: String): User {
        return mapper.load(User::class.java, document, id) ?: throw NotFoundException(
            USER_NOT_FOUND
        )
    }

}
