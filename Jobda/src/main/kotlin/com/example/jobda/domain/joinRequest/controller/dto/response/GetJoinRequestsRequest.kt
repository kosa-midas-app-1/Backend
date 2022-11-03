package com.example.jobda.domain.joinRequest.controller.dto.response

import java.util.UUID

/**
 *
 * GetJoinRequestsRequest
 *
 * @author ljcha
 * @date 2022-11-04
 * @version 1.0.0
 **/
data class GetJoinRequestsRequest(
    val request: List<RequestElement>
) {
    data class RequestElement(
        val id: UUID,
        val name: String,
        val email: String,
        val phoneNumber: String,
        val position: String
    )
}