package org.sisioh.trinity.domain.mvc.http

import org.specs2.mutable.Specification
import org.sisioh.trinity.domain.io.http.{Version, Method}
import org.sisioh.trinity.domain.mvc.action.SimpleAction
import scala.concurrent.Future
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RequestImplSpec extends Specification {

  "request" should {
    "has action as some" in {
      val method = Method.Get
      val uri = "/"
      val actionOpt = Some(new SimpleAction {
        def apply(request: Request): Future[Response] = Future.successful(Response())
      })
      val routeParams = Map.empty[String, String]
      val globalSettingsOpt = None
      val errorOpt = None
      val version = Version.Http11

      val request = new RequestImpl(method, uri, actionOpt, routeParams, globalSettingsOpt, errorOpt, version)
      request.method must_== method
      request.uri must_== uri
      request.actionOpt must_== actionOpt
      request.routeParams must_== routeParams
      request.globalSettingsOpt must_== globalSettingsOpt
      request.errorOpt must_== errorOpt
      request.protocolVersion must_== version
    }
    "execute action" in {
      val method = Method.Get
      val uri = "/"
      val responseFuture = Future.successful(Response())
      val action = new SimpleAction {
        def apply(request: Request): Future[Response] = responseFuture
      }
      val routeParams = Map.empty[String, String]
      val globalSettingsOpt = None
      val errorOpt = None
      val version = Version.Http11

      val request = new RequestImpl(method, uri, Some(action), routeParams, globalSettingsOpt, errorOpt, version)
      request.execute(action) must_== responseFuture
    }
    "be changed action" in {
      val method = Method.Get
      val uri = "/"
      val responseFuture = Future.successful(Response())
      val action = new SimpleAction {
        def apply(request: Request): Future[Response] = responseFuture
      }
      val routeParams = Map.empty[String, String]
      val globalSettingsOpt = None
      val errorOpt = None
      val version = Version.Http11

      val request = new RequestImpl(method, uri, None, routeParams, globalSettingsOpt, errorOpt, version)
      val newRequest = request.withAction(Some(action))
      newRequest must_== request
    }
  }


}
