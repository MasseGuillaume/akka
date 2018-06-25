/*
 * Copyright (C) 2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka

import akka.annotation.InternalApi

package object compat {
  // 2.13.0-M5: https://github.com/scala/bug/issues/10961
  @InternalApi private[akka] implicit class TraversableOnceExtensionMethods[A](
    private val self: TraversableOnce[A]) extends AnyVal {

    def copyToArray[B >: A](xs: Array[B], start: Int): xs.type = {
      val xsLen = xs.length
      val it = self.toIterator
      var i = start
      while (i < xsLen && it.hasNext) {
        xs(i) = it.next()
        i += 1
      }
      xs
    }
  }

  // 2.13.0-M5: https://github.com/scala/bug/issues/10961
  @InternalApi private[akka] implicit class IteratorExtensionMethods[A](
    private val self: Iterator[A]) extends AnyVal {

    def partition(p: A => Boolean): (Iterator[A], Iterator[A]) = {
      val (a, b) = self.duplicate
      (a.filter(p), b.filterNot(p))
    }
  } 
}
