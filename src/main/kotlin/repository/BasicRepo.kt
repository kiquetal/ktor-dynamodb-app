package repository

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

interface BasicRepo<T> {
    fun deleteEntiy(e:T)
    fun addEntity(e:T)
    fun findEntity(e:String)
}


abstract class DynamoDbRepo<T>(protected val tableName:String,val klass:Class<T>,val client:DynamoDbEnhancedAsyncClient): BasicRepo<T>
{

     lateinit var dynamoTable : DynamoDbAsyncTable<T>

    init {
        this.dynamoTable = client.table(tableName, TableSchema.fromBean(klass))
    }

      override fun deleteEntiy(e: T) {
        TODO("Not yet implemented")
    }

    override fun addEntity(e: T) {
        println("hola")
    }

    override fun findEntity(e: String) {
        TODO("Not yet implemented")
    }

}

