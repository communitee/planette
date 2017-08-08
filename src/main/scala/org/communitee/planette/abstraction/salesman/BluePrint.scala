//package org.communitee.planette.abstraction.salesman
//
///**
//  * Created by root on 29/06/17.
//  */
//trait BluePrint {
//  type MyApp = PlanetteApp with CreateAptitude
//
//
//}
//
//trait Task extends Creatable[String, Task]{
//  val name: String
//  override def func: (String) => Task = Task.apply
//}
//
//object Task {
//  def apply(name:String): Task = new Task {
//    override val name: String = name
//  }
//}
//
//
//trait CreateAptitude extends Can[Create] {
//  def create[A, What <: Creatable[A, What]]() = can.create[A, What]
//}
//
//trait Create extends ActionPRovider[Create]{
//
//  def create[A, What <: Creatable[A, What]]: A => What
//  override def instantiate: Create = Create.apply
//}
//
//object Create {
//  def apply() = new Create {
//    override def create[A, What <: Creatable[A, What]]: (A) => What = ???
//
//
//  }
//}
//
//trait Creatable[A, What]{
//  def func: A => What
//}
//
//
//object BluePrintApp extends App with BluePrint{
//  val myApp = new PlanetteApp with CreateAptitude {
//    override protected def can: Create = ???
//  }
//
//}
