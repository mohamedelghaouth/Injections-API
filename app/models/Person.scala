package models

import play.api.libs.json.{JsResult, JsSuccess, JsValue}

case class Person(
                      var firstName:String,
                      var lastName:String,
                      var age: Int) {

  implicit def reads(json: JsValue): JsResult[Person] = {
    val firstName = (json \ "firstName").as[String]
    val lastName = (json \ "lastName").as[String]
    val age = (json \ "age").as[Int]
    JsSuccess(Person(firstName, lastName, age))
  }
}