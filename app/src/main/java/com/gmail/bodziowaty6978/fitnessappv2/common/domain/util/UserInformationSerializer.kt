package com.gmail.bodziowaty6978.fitnessappv2.common.domain.util

import androidx.datastore.core.Serializer
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.UserInformation
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object UserInformationSerializer: Serializer<UserInformation> {

    override val defaultValue: UserInformation
        get() = UserInformation()

    override suspend fun readFrom(input: InputStream): UserInformation {
        return try {
            Json.decodeFromString(
                deserializer = UserInformation.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:Exception){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserInformation, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserInformation.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}