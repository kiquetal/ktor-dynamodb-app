package repository
import entities.Entity
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.*
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import java.util.function.Consumer

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
       return this.dynamoTable.getItem(itemEnhancedRequest).join()
    }
    fun getAllEntities(): ArrayList<Entity> {
        val list=ArrayList<Entity>()
        val scanEnhancedRequest= ScanEnhancedRequest.builder()
            .limit(10).build();

        val iterator = this.dynamoTable.scan(scanEnhancedRequest)
        println(iterator.toString())
        iterator.subscribe(Consumer { page->
            page.items().forEach(list::add)
        }).join();
        return list;
    }


}


