package com.kentvu.csproblems.data.solution

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter

interface KotlinSolution {

  fun logger(tag: String) =
    Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), tag)
}
