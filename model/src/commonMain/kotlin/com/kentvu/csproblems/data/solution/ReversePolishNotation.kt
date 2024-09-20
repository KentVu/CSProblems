package com.kentvu.csproblems.data.solution

object ReversePolishNotation: KotlinSolution {
  val ops: Map<String, (lhs: Int, rhs: Int) -> Int> = mapOf(
    "+" to { lhs, rhs -> lhs + rhs },
    "-" to { lhs, rhs -> lhs - rhs },
    "*" to { lhs, rhs -> lhs * rhs },
    "/" to { lhs, rhs -> lhs / rhs },
  )
  @Suppress("ArrayInDataClass") // will use the same array all the time.
  data class Expr(
    val arr: Array<String>,
    val oprPos: Int,
  ) {

    val kind: Kind get() =
      if (ops.containsKey(arr[oprPos])) Kind.Operation
      else Kind.Const
    val opr: String get() = arr[oprPos]
    val rhsPos: Int =
      oprPos - 1
    val rhs: Expr by lazy { Expr(arr, rhsPos) }
    val value: Int get() =
      if (kind == Kind.Const) opr.toInt()
      else error("$oprPos($opr) is not Const!")
    val lhsPos: Int by lazy {
      oprPos - rhs.size()
    }
    val lhs: Expr by lazy { Expr(arr, lhsPos) }

    private fun size(): Int {
      return if (kind == Kind.Const) 1
      else {
        val rhsSize = rhs.size()
        1 + rhsSize + lhs(rhsSize).size()
      }
    }

    private fun lhs(rhsSize: Int) = Expr(arr, oprPos - rhsSize)
  }

  enum class Kind {
    Operation, Const
  }

  operator fun invoke(input: String): Int {
    val arr = input.split("""\s*,\s*""".toRegex()).toTypedArray()
    val i = arr.lastIndex
    //val rhs = Expr(arr, i - 1)
    val expr = Expr(arr, i, /*rhs*/)
    return rpn(expr)
  }

  private fun rpn(expr: Expr): Int {
    if (expr.kind == Kind.Const)
      return expr.value
    else if (expr.rhs.kind == Kind.Const)
      return calc(expr.opr, expr.rhs.value, rpn(expr.copy(oprPos = expr.rhsPos - 1)))
    else {
      return calc(expr.opr, rpn(expr.rhs), rpn(expr.lhs))
    }
  }

  private fun rpn(arr: Array<String>, opi: Int, lhsi: Int, rhsi: Int): Int {
    TODO()
  }

  private fun calc(opr: String, rhs: Int, lhs: Int): Int {
    return when {
      ops.containsKey(opr) -> ops[opr]!!(lhs, rhs)
      else -> error("Unknown operator $opr")
    }
  }
}
