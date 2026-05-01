package com.example.dementiaapp.data

import androidx.datastore.core.Serializer
import com.example.dementiaapp.domain.configuration.DefaultPermissions
import com.example.dementiaapp.domain.models.UserPermissions
import com.example.dementiaapp.domain.models.UserRole
import java.io.InputStream
import java.io.OutputStream

class UserPermissionsSerializer(
    val userRole: UserRole
): Serializer<UserPermissions> {
    override val defaultValue: UserPermissions
        get() =
            if (userRole == UserRole.CAREGIVER)
                DefaultPermissions.defaultCaregiverPermissions
            else
                DefaultPermissions.defaultCareRecipientPermissions


    override suspend fun readFrom(input: InputStream): UserPermissions {
        TODO("Not yet implemented")
    }

    override suspend fun writeTo(
        t: UserPermissions,
        output: OutputStream
    ) {
        TODO("Not yet implemented")
    }

}