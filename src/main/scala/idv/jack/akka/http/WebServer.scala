package idv.jack.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol

import scala.io.StdIn

object WebServer {

  case class Person(name: String, favoriteNumber: Int)

  object PersonJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val PortofolioFormats = jsonFormat2(Person)
  }

  def main(args: Array[String]): Unit = {
     implicit val system = ActorSystem("my-system")
     implicit val materializer = ActorMaterializer()
     implicit val executionContext = system.dispatcher

     val route =
       path("hello") {
         get {
           complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to Akka</h1>"))
         }
       } ~
       path("testjson") {
         post {
           import PersonJsonSupport._
           entity(as[Person]) { person =>
             complete(HttpEntity(ContentTypes.`application/json`, "POST AND JSON Test " + person.name.toUpperCase()))
           }
         }
       }
     val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

     println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
     StdIn.readLine()
     bindingFuture
          .flatMap(_.unbind())
            .onComplete(_ => system.terminate())

  }
}
