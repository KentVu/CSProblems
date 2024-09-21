package com.kentvu.csproblems.data.solution

import kotlin.properties.Delegates

object ReversePolishNotation: KotlinSolution {
  private val log = logger("ReversePolishNotation")
  val ops: Map<String, (lhs: Int, rhs: Int) -> Int> = mapOf(
    "+" to { lhs, rhs -> lhs + rhs },
    "-" to { lhs, rhs -> lhs - rhs },
    "*" to { lhs, rhs -> lhs * rhs },
    "/" to { lhs, rhs -> lhs / rhs },
  )

  enum class Kind {
    Operation, Const
  }

  operator fun invoke(input: String): Int {
    val arr = input.split("""\s*,\s*""".toRegex()).toTypedArray()
    return Resolution(arr).result()
  }

  class Resolution(private val arr: Array<String>) {
    private val exprCache = mutableMapOf<Int, Expr>()

    inner class Expr(
      val oprPos: Int,
    ) {

      val kind: Kind by lazy {
        if (ops.containsKey(arr[oprPos])) Kind.Operation
        else Kind.Const
      }
      val opr: String get() = arr[oprPos]
      val rhsPos: Int =
        oprPos - 1
      val value: Int by lazy {
        if (kind == Kind.Const) opr.toInt()
        else error("$oprPos($opr) is not Const!")
      }
      var size: Int? = null

      override fun toString(): String {
        return "${Expr::class.simpleName}:$oprPos:$size"
      }
    }

    fun Expr.get(oprPos: Int): Expr {
      return exprCache.getOrPut(oprPos) { Expr(oprPos) }
    }

    val Expr.rhs: Expr get() =
      exprCache.getOrPut(rhsPos) { Expr(rhsPos) }
    private fun Expr.lhs(rhsSize: Int): Expr {
      val lhsPos = oprPos - 1 - rhsSize
      return exprCache.getOrPut(lhsPos){
        Expr(lhsPos)
      }
    }

    private fun Expr.lhsPos(): Int {
      return oprPos - 1 - rhs.size()
    }
    private val Expr.lhs: Expr get() {
      val lhsPos = lhsPos()
      return exprCache.getOrPut(lhsPos){ Expr(lhsPos) }
    }

    private fun Expr.size(): Int {
      return size
          ?: if (kind == Kind.Const) 1
          else {
            val rhsSize = rhs.size()
            1 + rhsSize + lhs(rhsSize).size()
          }.also { size = it }
    }

    fun result(): Int {
      val i = arr.lastIndex
      val expr = Expr(i)
      exprCache[i] = expr
      return rpn(expr)
    }

    private fun rpn(expr: Expr): Int {
      return if (expr.kind == Kind.Const)
        expr.value
      else {
        val lhs = expr.lhs
        if (expr.rhs.kind == Kind.Const) {
          calc(expr.opr, expr.rhs.value, rpn(lhs))
        } else {
          calc(expr.opr, rpn(expr.rhs), rpn(lhs))
        }
      }
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
