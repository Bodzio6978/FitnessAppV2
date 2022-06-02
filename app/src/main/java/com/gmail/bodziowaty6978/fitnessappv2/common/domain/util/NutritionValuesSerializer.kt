package com.gmail.bodziowaty6978.fitnessappv2.common.domain.util

import androidx.datastore.core.Serializer
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.NutritionValues
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object NutritionValuesSerializer: Serializer<NutritionValues> {

    override val defaultValue: NutritionValues
        get() = NutritionValues()

    override suspend fun readFrom(input: InputStream): NutritionValues {
        return try {
            Json.decodeFromString(
                deserializer = NutritionValues.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:Exception){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: NutritionValues, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = NutritionValues.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}