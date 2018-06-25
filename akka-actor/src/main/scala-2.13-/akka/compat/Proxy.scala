/**
 * Copyright (C) 2009-2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.compat

import scala.collection.GenTraversableLike
import scala.collection.generic
import scala.collection.immutable

private[akka] trait ClassNameProxy { _: GenTraversableLike[_, _] =>
  def className: String
  override def stringPrefix: String = className
}

private[stm] trait GrowableProxy[-A] { _: generic.Growable[A] =>
  def addOne(elem: A): this.type
  @`inline` override final def += (elem: A): this.type = addOne(elem)

  def addAll(xs: IterableOnce[A]): this.type
  @`inline` final def ++= (xs: IterableOnce[A]): this.type = addAll(xs)
}
