package org.sisioh.trinity.domain.io.http

import com.google.common.base.Splitter
import scala.collection.JavaConversions.iterableAsScalaIterable

/**
 * Represents the Ordering implementation for Accept.
 */
object AcceptOrdering extends Ordering[String] {

  private def getWeight(str: String): Double = {
    val parts = Splitter.on(';').split(str).toArray
    if (parts.length < 2) {
      1.0
    } else {
      try {
        Splitter.on("q=").split(parts(1)).toArray.last.toFloat
      } catch {
        case e: java.lang.NumberFormatException =>
          1.0
      }
    }
  }

  def compare(a: String, b: String) =
    getWeight(b) compare getWeight(a)

}
