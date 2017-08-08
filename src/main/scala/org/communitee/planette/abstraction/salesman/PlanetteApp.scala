package org.communitee.planette.abstraction
package salesman


import OfImpl._
import PieceImpl._
import WorkImpl._
import Can._
import Create._
import Task._
import A._
import Transforms._
import Schedule._
import Into._
import TodoList._TodoList


object PlanetteApp extends Entity with App  {
  //val task =  a piece of work


  val a: A = new A {}

  this.transforms.a.todoList.into.a.schedule

  val aa = a.piece.of.work

  val createApt = this.can.create.a.task

  println(aa.terms.reverse)
  println(createApt.terms.reverse)


  val u = 0


}

//trait Term
//trait A {
//  def a: Term => Term
//}
//
//trait piece{
//  def of(term: Term):
//}

