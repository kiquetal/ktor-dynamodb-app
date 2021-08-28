package me.cresterida.converters

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.EnhancedAttributeValue
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.*
import java.time.format.DateTimeFormatter


class MyInstanceConverter: AttributeConverter<ZonedDateTime> {

    override fun transformTo(input: AttributeValue?): ZonedDateTime {



        return  ZonedDateTime.parse(input?.s());
    }

    override fun type(): EnhancedType<ZonedDateTime> {
        return EnhancedType.of(ZonedDateTime::class.java);
    }

    override fun attributeValueType(): AttributeValueType {
        return AttributeValueType.S;
    }

    override fun transformFrom(input: ZonedDateTime?): AttributeValue {

        val dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
            .format(input)
        return AttributeValue.builder().s(dateTimeFormatter).build()
    }

}


