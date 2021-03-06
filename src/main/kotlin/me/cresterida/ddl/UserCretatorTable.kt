package me.cresterida.ddl

import me.cresterida.clients.Clients
import me.cresterida.entities.Entity
import me.cresterida.entities.User
import org.slf4j.LoggerFactory
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.CreateTableEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput

abstract class GenericTableCreator<T>(private val tableName:String,private val client:DynamoDbEnhancedAsyncClient,

    val clan: Class<T>){
    private  val READ_CAPACITY_UNITS = 1L
    private  val WRITE_CAPACITY_UNITS = 1L
    abstract fun createEnhancedRequest():CreateTableEnhancedRequest
    val log = LoggerFactory.getLogger(GenericTableCreator::class.java)
    protected lateinit var table:DynamoDbAsyncTable<T>
     fun createTable() {
        table = client.table(tableName, TableSchema.fromBean(clan))
        table.createTable(createEnhancedRequest())
            .thenAccept {
                log.info("Table ${clan.simpleName} created")
            }
            .exceptionally {
                log.error("Error ${it.message}" )
                throw it
            }
            .join()
    }
    val provisionedThroughput: ProvisionedThroughput = ProvisionedThroughput
        .builder()
        .readCapacityUnits(READ_CAPACITY_UNITS)
        .writeCapacityUnits(WRITE_CAPACITY_UNITS)
        .build()


}




class EntityCreatorTable: GenericTableCreator<Entity>(Entity::class.java.simpleName  ,Clients.enhancedDynamoClient(Clients.dynamoLocalClient()),Entity::class.java)
{
    override fun createEnhancedRequest(): CreateTableEnhancedRequest {
        return CreateTableEnhancedRequest
            .builder()
            .provisionedThroughput(provisionedThroughput)
            .build()

    }
}

class UserCreatorTable: GenericTableCreator<User>(User::class.java.simpleName  ,Clients.enhancedDynamoClient(Clients.dynamoLocalClient()),User::class.java)
{
    override fun createEnhancedRequest(): CreateTableEnhancedRequest {
        return CreateTableEnhancedRequest
            .builder()
            .provisionedThroughput(provisionedThroughput)
            .build()

    }
}
