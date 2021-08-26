package me.cresterida.converters

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.EnhancedAttributeValue
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class MyInstanceConverter: AttributeConverter<LocalDateTime> {
    override fun transformFrom(input: LocalDateTime?): AttributeValue {

        val format = DateTimeFormatter.ISO_DATE_TIME.
        withZone(ZoneId.of("UTC"))
            .format(input);

        println("here "+ format)
        return AttributeValue.builder().s(format).build()
    }

    override fun transformTo(input: AttributeValue?): LocalDateTime {


        val formatter = DateTimeFormatter.ISO_DATE_TIME.
        withZone(ZoneId.of("UTC"));

        return LocalDateTime.parse(input?.s(),formatter)

    }

    override fun type(): EnhancedType<LocalDateTime> {
        return EnhancedType.of(LocalDateTime::class.java);
    }

    override fun attributeValueType(): AttributeValueType {
        return AttributeValueType.S;
    }

}


