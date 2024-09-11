package com.kentvu.utils

class ListWithSelection<T>(
  private val list: List<T>,
  val selectedItem: T? = null
) : List<T> by list {

  fun select(item: T?): ListWithSelection<T> {
    return list.withSelection(item)
  }

  fun select(id: Int): ListWithSelection<T> = select(list[id])

  fun updateList(l: List<T>): ListWithSelection<T> {
    return ListWithSelection(l, selectedItem)
  }
}

inline fun <E> List<E>.withSelection(item: E? = null) = ListWithSelection(this, item)

inline fun <T> listWithSelectionOf(): ListWithSelection<T> = ListWithSelection(emptyList())
