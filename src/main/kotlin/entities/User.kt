package entities
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.InstantAsStringAttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

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
    @get:DynamoDbConvertedBy(InstantAsStringAttributeConverter::class)
    var date:Instant?=null
)


class Mien:AttributeConverter<Instant>{
    override fun transformFrom(input: Instant?): AttributeValue {

        return AttributeValue.builder().build()
    }

    override fun transformTo(input: AttributeValue?): Instant {
        TODO("Not yet implemented")
    }

    override fun type(): EnhancedType<Instant> {
        TODO("Not yet implemented")
    }

    override fun attributeValueType(): AttributeValueType {
        TODO("Not yet implemented")
    }

}


