package entities
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.EnhancedAttributeValue
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.InstantAsStringAttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class User(val name:String,var age:Int)


@DynamoDbBean
data class PatientInfos(val allData:Map<String,String>?=null)

@DynamoDbBean
data class Entity(
    @get:DynamoDbPartitionKey
    var pk: String? = null,
    @get:DynamoDbSortKey
    var sk: String? = null,
    @get:DynamoDbConvertedBy(Mien::class)
    var date:Instant?=null,

)


class Mien:AttributeConverter<Instant>{
    override fun transformFrom(input: Instant?): AttributeValue {

    val format = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"))
        .format(input);

        return EnhancedAttributeValue.fromString(format).toAttributeValue()
    }

    override fun transformTo(input: AttributeValue?): Instant {

  return Instant.from(DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"))
      .parse(input?.s()))



    }

    override fun type(): EnhancedType<Instant> {
        return EnhancedType.of(Instant::class.java);
    }

    override fun attributeValueType(): AttributeValueType {
        return AttributeValueType.S;
    }

}


