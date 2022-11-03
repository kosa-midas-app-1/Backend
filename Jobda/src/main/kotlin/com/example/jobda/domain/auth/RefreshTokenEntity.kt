package com.example.jobda.domain.auth

import com.example.jobda.domain.auth.type.Authority
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.UUID
import javax.validation.constraints.NotNull

/**
 *
 * RefreshTokenEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@RedisHash("tbl_refresh_token")
data class RefreshTokenEntity(

    @Id
    val token: String,

    @field:NotNull
    val userId: UUID,

    @field:NotNull
    val authority: Authority,

    @field:NotNull
    @TimeToLive
    val expirationTime: Int

)