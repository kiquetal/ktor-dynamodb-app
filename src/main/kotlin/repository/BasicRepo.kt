package repository

import entities.Entity
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

interface BasicRepo<T> {
    fun deleteEntiy(e:T)
    fun addEntity(e:T)
    fun findEntity(e:String)
}



abstract class DynamoDbRepo<T>(private val tableName:String, private val klass:Class<T>, private val client:DynamoDbEnhancedAsyncClient): BasicRepo<T>
{
    lateinit var dynamoTable:DynamoDbAsyncTable<T>
    init {
         this.dynamoTable  = client.table(tableName, TableSchema.fromBean(klass))

    }

}

