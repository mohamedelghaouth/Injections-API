package controllers

import models.{Astrazeneca, Date, Fist, Injection, InjectionStatus, InjectionType, Injections, Moderna, Person, Pfizer, Second, Third}
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Reads.jodaLocalDateReads
import play.api.libs.json.{JsObject, JsResult, JsString, JsSuccess, JsValue, Json, Reads, Writes, __}

import javax.inject._
import play.api.mvc._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  implicit val DateWrites = new Writes[Date] {
    def writes(date: Date) = JsString(
      date.toString(),
    )
  }

  implicit val injectionTypeWrites = new Writes[InjectionType] {
    def writes(injectionType: InjectionType) = JsString(
       injectionType.toString(),
    )
  }
  implicit val injectionStatusWrites = new Writes[InjectionStatus] {
    def writes(injectionStatus: InjectionStatus) = JsString(
      injectionStatus.toString(),
    )
  }
  implicit val injectionWrites = new Writes[Injection] {
    def writes(injection: Injection) = Json.obj(
      "Id"  -> injection.id,
              "Date" -> injection.injectionDate,
              "Type" -> injection.injectionType,
              "Status" -> injection.status,
    )
  }

  implicit def readsInjectionStatus(json: JsValue): JsResult[InjectionType] = {
    json.toString() match {
      case  Pfizer.toString =>  JsSuccess(Pfizer)
      case  Astrazeneca.toString => JsSuccess(Astrazeneca)
      case  Moderna.toString => JsSuccess(Moderna)
    }
  }
  implicit def readsInjectionStatus(json: JsValue): JsResult[InjectionStatus] = {
    json.toString() match {
      case  Fist.toString =>  JsSuccess(Fist)
      case  Second.toString => JsSuccess(Second)
      case  Third.toString => JsSuccess(Third)
    }
  }


  def getAll() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("injections" -> Json.toJson(Injections.getInjects())))
  }

  def getById(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("injections" -> Json.toJson(Injections.getInjects().get(id))))
  }
  import play.api.libs.json.Json

  def addInjection() = Action { implicit request =>
    val json = request.body.asJson.get
    val injection = json.as[Injection]
    Ok(Json.obj("injections" -> Json.toJson(Injections.getInjects())))
  }

}
