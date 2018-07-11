/*
 * Copyright (C) 2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka

import akka.annotation.InternalApi

package object compat {
  // should be available in 2.13.0-M5: https://github.com/scala/bug/issues/10961
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
}
