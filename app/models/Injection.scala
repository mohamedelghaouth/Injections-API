package models

import play.api.libs.json.{JsResult, JsSuccess, JsValue}


case class Injection( var id:Long,
                      var injectionDate: Date,
                      var injectionType: InjectionType,
                      var status: InjectionStatus,
                      var person: Person){
  override def toString(): String = "(" + id + "," + injectionType + ")";

  implicit def reads(json: JsValue): JsResult[Injection] = {
    val id = (json \ "id").as[Long]
    val injectionDate = (json \ "injectionDate").as[Date]
    val injectionType = (json \ "injectionType").as[InjectionType]
    val status = (json \ "status").as[InjectionStatus]
    val person = (json \ "person").as[Person]
    JsSuccess(Injection(id, injectionDate, injectionType, status, person))
  }
}
