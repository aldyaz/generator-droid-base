package com.example.domain.base.exception


sealed class Failure {
    object NetworkFailure : Failure()
    object ServerFailure : Failure()
    abstract class FeatureFailure() : Failure()
}