package org.sisioh.trinity.domain.mvc.http

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.sisioh.trinity.domain.io.http.{Charset, Charsets}

case class JSON4SRenderer(jValue: JValue, charset: Charset = Charsets.UTF_8) extends ResponseRenderer {

  def render(responseBuilder: ResponseBuilder): Unit =
    responseBuilder.
      withContent(compact(jValue), charset).
      withContentType("application/json; charset=" + charset.toString.toLowerCase)

}
