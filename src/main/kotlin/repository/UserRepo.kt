package repository
import entities.Entity
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key

class UserRepo(client: DynamoDbEnhancedAsyncClient) :DynamoDbRepo<Entity>(Entity::class.java.simpleName,Entity::class.java,client) {
    override fun addEntity(e: Entity) {
        this.dynamoTable.putItem(e).thenAccept {
            println("adentro")
        }.join()

    }
    override fun deleteEntiy(e: Entity) {
        val key:Key = Key.builder().partitionValue(e.pk).build()
        this.dynamoTable.deleteItem(key).thenAccept { println("borrado") }.join()
    }

    override fun findEntity(e: String) {

    }


}


