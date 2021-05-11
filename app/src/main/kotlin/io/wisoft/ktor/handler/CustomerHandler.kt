package io.wisoft.ktor.handler

import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import io.wisoft.ktor.config.query
import io.wisoft.ktor.domain.Customer
import io.wisoft.ktor.domain.Customers
import io.wisoft.ktor.dto.CustomerRequest
import io.wisoft.ktor.dto.CustomerResponse

@KtorExperimentalAPI
class CustomerHandler {
    suspend fun getAll() = query {
        Customer.all().map(CustomerResponse::of).toList()
    }

    suspend fun getById(id: Long) = query {
        Customer.findById(id)?.let { CustomerResponse.of(it) }
            ?: throw NotFoundException()
    }

    suspend fun new(request: CustomerRequest) = query {
        checkDuplicate(request.email)
            .new {
                email = request.email
                firstName = request.firstName
                lastName = request.lastName
            }
    }

    suspend fun renew(id: Long, request: CustomerRequest) = query {
        val customer = Customer.findById(id) ?: throw NotFoundException()
        customer.apply {
            firstName = request.firstName
            lastName = request.lastName
        }
    }

    suspend fun delete(id: Long) = query {
        Customer.findById(id)?.delete() ?: throw NotFoundException()
    }

    private fun checkDuplicate(email: String) =
        Customer.takeIf {
            it.find { Customers.email eq email }.empty()
        } ?: throw BadRequestException("이미 사용중인 이메일입니다.")
}
