package com.example.jobda.common

import com.fasterxml.uuid.Generators
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 *
 * BaseUUIDEntity
 *
 * @author ljcha
 * @date 2022-11-03
 * @version 1.0.0
 **/
@MappedSuperclass
abstract class BaseUUIDEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    val id: UUID = Generators.timeBasedGenerator().generate()

}