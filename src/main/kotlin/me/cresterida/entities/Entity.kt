package me.cresterida.entities
import me.cresterida.converters.MyInstanceConverter
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import java.time.ZonedDateTime



@DynamoDbBean
data class Entity(
    @get:DynamoDbPartitionKey
    var pk: String? = null,
    @get:DynamoDbSortKey
    var sk: String? = null,
    @get:DynamoDbConvertedBy(MyInstanceConverter::class)
    var date:ZonedDateTime?=null,


)
@DynamoDbBean
data class User(
     @get:DynamoDbPartitionKey
     var email: String? = null,
     @get:DynamoDbSortKey
     var passWd: String? =null,
     @get:DynamoDbConvertedBy(MyInstanceConverter::class)
     var modifiedTime: ZonedDateTime?=null,
    @get:DynamoDbConvertedBy(MyInstanceConverter::class)
    var createdTime: ZonedDateTime?=null
)

