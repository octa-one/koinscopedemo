package com.orcchg.direcall.base.usecase

import com.orcchg.direcall.scheduler_api.SchedulersFactory

abstract class UseCase(protected val schedulersFactory: SchedulersFactory)
