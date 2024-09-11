package com.kentvu.csproblems

interface Playground {
  val algos: List<String>
  fun invoke(algo: String, vararg args: Any?): Any?

}
