package repository
import entities.Entity
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest

class UserRepo(private val client: DynamoDbEnhancedAsyncClient) :DynamoDbRepo<Entity>(Entity::class.java.simpleName,Entity::class.java,client) {
    override fun getKey(e: Entity): Key {
        return Key.builder().partitionValue(e.pk).build()
    }
    override fun findEntity(e: Entity):Entity {
        val key = Key.builder()
            .partitionValue(e.pk)
            .sortValue(e.sk)
            .build()
        val itemEnhancedRequest=GetItemEnhancedRequest.builder()
            .key(key).build()


            val indx = this.dynamoTable.index("myIndice")

            val q = QueryConditional.
       return this.dynamoTable.getItem(itemEnhancedRequest).join()
    }


}


