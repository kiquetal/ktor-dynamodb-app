package me.cresterida.repository
import me.cresterida.entities.Entity
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.*
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
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
    fun findEntityByPk(key:String): Entity
    {
        val k = Key.builder().partitionValue(key)
            .build()

        val itemEnhancedRequest=GetItemEnhancedRequest.builder()
        .key(k).build()

      val q= QueryEnhancedRequest.builder().queryConditional(
        QueryConditional.
                    sortGreaterThan(
                                Key.builder()
                                    .partitionValue(key)
                                    .sortValue("1").build())
      ).build()

         val  l= arrayListOf<Entity>()
        this.dynamoTable.query(q).items().subscribe(l::add).join()
        return  l[0];

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

