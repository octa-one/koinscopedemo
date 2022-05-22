package com.orcchg.direcall.scheduler_api

import io.reactivex.Scheduler

interface SchedulersFactory {

    fun io(): Scheduler

    fun main(): Scheduler

    fun useCase(): Scheduler
}
