package com.example.jobda.domain.auth.repository

import com.example.jobda.domain.auth.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 * RefreshTokenRepository
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@Repository
interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String> {
}