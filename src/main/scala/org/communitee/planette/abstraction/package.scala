package org.communitee.planette

/**
  * Created by root on 01/08/17.
  */
package object abstraction {
  trait Term {
    val terms: scala.collection.mutable.MutableList[Term] = scala.collection.mutable.MutableList(this)
    val neededInoformation: scala.collection.immutable.List[Term] = ???

    def and[B <: Term](a: B): B = {
      a.terms ++= this.terms
      a
    }

    override def toString: String = {
      val simpleName = this.getClass.getSimpleName
      if(simpleName.contains("anon"))
        this.getClass.getInterfaces()(0).getSimpleName
      else
        simpleName.replace("$", "")

    }
  }

  trait Thing extends Term

  trait A extends Term

  trait Piece extends Term {
    def piece: Piece = ???
  }

  trait Work extends Term

  trait Possesable extends Term

  trait Relation extends Term

  trait Membership extends Relation

  trait Of extends Term {

    def of: Of = ???
  }

  trait Task extends Term

  trait Create extends Term

  trait Entity extends Term

  trait Can extends Term

  trait Location extends Term

  trait Duration extends Term

  trait Description extends Term

  trait Transform extends Term

  trait Transforms extends Transform

  trait List extends Term

  trait TodoList extends List

  trait To extends Term

  trait Schedule extends Term
}
