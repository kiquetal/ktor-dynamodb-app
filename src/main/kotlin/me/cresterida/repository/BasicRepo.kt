package me.cresterida.repository

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest

interface BasicRepo<T> {
    fun deleteEntiy(e:T)
    fun addEntity(e:T)
    fun findEntity(e:T): T
}



abstract class DynamoDbRepo<T>(private val tableName:String, private val klass:Class<T>, private val client:DynamoDbEnhancedAsyncClient): BasicRepo<T>
{
    lateinit var dynamoTable:DynamoDbAsyncTable<T>
    init {
         this.dynamoTable  = client.table(tableName, TableSchema.fromBean(klass))

    }
    override fun addEntity(e: T) {
        val putItemRequest = PutItemEnhancedRequest.builder(klass)
            .item(e).build()
        dynamoTable.putItem(putItemRequest).join()
     }
    override fun deleteEntiy(e: T) {
     val deleteItemRequest = DeleteItemEnhancedRequest.builder().key(getKey(e)).build()
        dynamoTable.deleteItem(deleteItemRequest).join();
    }

    abstract fun getKey(e:T):Key

}

