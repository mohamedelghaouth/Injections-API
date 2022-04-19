package models


 case object Injections{
  def getInjects(): Map[Long, Injection] = injects

  private var injects: Map[Long, Injection] = Map[Long, Injection]()

  {
    var p1 = new Person("Med", "elg", 24)
    var p2 = new Person("Kévin", "Dumanoir", 25)
    var p3 = new Person("Paul", "Ghibeaux", 26)

    var i1 = new Injection(1, new Date(1, 12, 2022), Pfizer, Fist, p1)
    var i2 = new Injection(2, new Date(1, 12, 2022), Astrazeneca, Second, p2)
    var i3 = new Injection(3, new Date(1, 12, 2022), Moderna, Third, p3)

    injects += (1L -> i1)
    injects += (2L -> i2)
    injects += (3L -> i3)
  }

   def deleteInjection(id: Long) =  injects -= id

   def addIjection(i: Injection) =  {
     val id = (injects.size).toLong
     injects += (id -> i)
   }
}

