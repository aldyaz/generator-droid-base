package com.example.domain.base.exception


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */

sealed class Failure {
    object NetworkFailure : Failure()
    object ServerFailure : Failure()
    abstract class FeatureFailure() : Failure()
}