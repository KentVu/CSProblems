package com.kentvu.csproblems.data.solution

object MapTimeCode : KotlinSolution {
  class TimedMap {
    val map = mutableMapOf<String, Map<Int, String>>()

    fun set(key: String, value: String, time: Int) {
      val bucket = map.getOrPut(key) { mapOf() }.toMutableMap()
      bucket[time] = value
      map[key] = bucket.entries.sortedBy { it.key }.associate { it.toPair() }
    }

    fun get(key: String, time: Int): String? {
      return map[key]?.let { bucket ->
        val keys = bucket.keys.toList()
        val i = keys.binarySearch(time)
        //bucket[time] ?: bucket[keys[i - 1]]
        //if (i < 0) bucket[keys[-i - 2]] else bucket[keys[i]]
        //val key = if (i < 0) if (i < -2) -i - 2 else null
        if (i == -1) null
        else if (i < 0) bucket[keys[-i - 2]]
        else bucket[keys[i]]
      }
    }

  }

  override fun invoke(input: String): Any {
    val m = TimedMap()
    val res = StringBuilder()
    input.lines().forEach { line ->
      val (op, param) = line.split(":").let { it[0] to it[1] }
      val args = param.split("\\s*,\\s*".toRegex())
      when (op) {
        "set" -> {
          res.appendLine(line)
          m.set(args[0], args[1], args[2].toInt())
        }

        "get" -> {
          res.append("$line -> ")
          val elm = m.get(args[0], args[1].toInt())
          res.appendLine(elm)
        }

        else -> {}
      }
    }
    return res
  }
}
