package me.cresterida.entities
import com.google.gson.annotations.SerializedName
import me.cresterida.converters.MyInstanceConverter
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

data class User(val name:String,var age:Int)


@DynamoDbBean
data class Entity(
    @get:DynamoDbPartitionKey
    var pk: String? = null,
    @get:DynamoDbSortKey
    var sk: String? = null,
    @get:DynamoDbConvertedBy(MyInstanceConverter::class)

    var date:LocalDateTime?=null,

)

