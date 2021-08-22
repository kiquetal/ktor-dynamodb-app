package ddl

import clients.Clients
import entities.CustomerPersist
import entities.User
import org.slf4j.LoggerFactory
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.model.CreateTableEnhancedRequest

abstract class GenericTableCreator<T>(private val tableName:String,private val client:DynamoDbEnhancedAsyncClient,
    val clan: Class<T>){
    abstract fun createEnhancedRequest():CreateTableEnhancedRequest
    val log = LoggerFactory.getLogger(GenericTableCreator::class.java)
    open fun createTable() {
        val t: DynamoDbAsyncTable<T> = client.table(tableName, TableSchema.fromBean(clan))
        t.createTable()
    }


}

class UserCreatorTable: GenericTableCreator<CustomerPersist>("customerTable",Clients.enhancedDynamoClient(Clients.dynamoLocalClient()),CustomerPersist::class.java) {
    override fun createEnhancedRequest(): CreateTableEnhancedRequest {
        TODO("Not yet implemented")
    }
}


