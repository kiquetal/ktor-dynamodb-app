package me.cresterida.repository

import me.cresterida.entities.User
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key

class UserRepo(private val client: DynamoDbEnhancedAsyncClient) :DynamoDbRepo<User>(
    User::class.java.simpleName,
    User::class.java,client) {
    override fun findEntity(e: User): User {

        return User()

    }

    override fun getKey(e: User): Key {

        return Key.builder().partitionValue(e.email).sortValue(e.passwd).build()
    }


}

