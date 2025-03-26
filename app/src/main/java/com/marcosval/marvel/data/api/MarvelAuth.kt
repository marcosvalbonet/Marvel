package com.marcosval.marvel.data.api

import java.math.BigInteger
import java.security.MessageDigest


object MarvelAuth {

    private const val MARVEL_PUBLIC_KEY = "801d8123806973acb5e2e930cb5f0bda"
    private const val MARVEL_PRIVATE_KEY = "c39cf89ede4892c756d3f66b4e6ab1e7dd44d9dc"

    fun generateAuthParams(): Map<String, String> {
        val timeStamp = System.currentTimeMillis().toString()
        val publicKey = MARVEL_PUBLIC_KEY
        val privateKey = MARVEL_PRIVATE_KEY
        val input = "$timeStamp$privateKey$publicKey"
        val hash = generateMd5Hash(input)

        println("ts: $timeStamp")
        println("Hash: $hash")

        return mapOf(
            "ts" to timeStamp,
            "apikey" to publicKey,
            "hash" to hash
        )
    }

    private fun generateMd5Hash(input: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(input.toByteArray()))
            .toString(16).padStart(32, '0')
    }
}