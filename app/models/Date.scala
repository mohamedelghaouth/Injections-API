package models

import play.api.libs.json.{JsResult, JsSuccess, JsValue}

class Date (
               var year: Int,
               var month: Int,
               var day:   Int
             ) {

    override def toString: String = {
      s"$day/$month/$year"
    }

   def reads(json: JsValue): JsResult[Date] = {
    val year = (json \ "year").as[Int]
    val month = (json \ "month").as[Int]
    val day = (json \ "day").as[Int]
    JsSuccess(new Date(year, month, day))
  }
}
