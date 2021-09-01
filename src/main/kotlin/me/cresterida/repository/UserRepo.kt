package me.cresterida.repository

import me.cresterida.entities.User
import me.cresterida.routes.myRoutes
import org.mindrot.jbcrypt.BCrypt
import org.slf4j.LoggerFactory
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest
import java.time.ZoneId
import java.time.ZonedDateTime

class UserRepo(private val client: DynamoDbEnhancedAsyncClient) :DynamoDbRepo<User>(

    User::class.java.simpleName,
    User::class.java,client) {

    val l = LoggerFactory.getLogger(this::class.java)

    override fun findEntity(e: User): User {

        return User()

    }

    override fun addEntity(e: User) {

        e.createdTime= ZonedDateTime.now(ZoneId.of("UTC"))
        e.passwd = BCrypt.hashpw(e.passwd,BCrypt.gensalt(10))
        val request = PutItemEnhancedRequest.builder(User::class.java)
        request.conditionExpression(Expression.builder()
            .expression("attribute_not_exists(email)")
            .build())


        request.item(e)

            this.dynamoTable.putItem(request.build()).join()





    }
    override fun getKey(e: User): Key {

        return Key.builder().partitionValue(e.email).sortValue(e.passwd).build()
    }


}

