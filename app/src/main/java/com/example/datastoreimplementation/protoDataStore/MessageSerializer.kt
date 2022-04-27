package com.example.datastoreimplementation.protoDataStore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.datastoreimplemntation.MessagePreference
import java.io.InputStream
import java.io.OutputStream

object MessageSerializer : Serializer<MessagePreference> {


    override suspend fun readFrom(input: InputStream): MessagePreference {
        try {
            return MessagePreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: MessagePreference, output: OutputStream) = t.writeTo(output)

    override val defaultValue: MessagePreference
        get() = MessagePreference.getDefaultInstance()

}