package models

sealed trait InjectionStatus
case object Fist extends  InjectionStatus
case object Second extends InjectionStatus
case object Third extends InjectionStatus

sealed trait InjectionType
case object Pfizer extends  InjectionType
case object Astrazeneca extends InjectionType
case object Moderna extends InjectionType
