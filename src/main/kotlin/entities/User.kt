package entities
import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import java.time.Instant

data class User(val name:String,var age:Int)


@DynamoDbBean
data class PatientInfos(val allData:Map<String,String>?=null)

@DynamoDbBean
data class Entity(
    @get:DynamoDbPartitionKey
    var pk: String? = null,
    @get:DynamoDbSortKey
    var sk: String? = null,
    @get:DynamoDbUpdateBehavior(UpdateBehavior.WRITE_IF_NOT_EXISTS)
    var date:Instant?=null
)
