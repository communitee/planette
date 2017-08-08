package org.communitee.planette.abstraction

import org.communitee.planette.abstraction.salesman.{A, OfImpl, PieceImpl}

import scala.collection.Iterator
import scala.collection.immutable.Seq

/**
  * Created by root on 29/06/17.
  */
package object salesman {


  implicit class RichValue(val v: Int) extends AnyVal {
    def asFancyString: String = "{v}!!!"
  }

  object A {
    implicit class _A(val create: Create) extends AnyVal{
      def a: A = {
        lazy val ai: A = {
          val newA = new A{}

          create and newA
        }
        ai
      }
    }

    implicit class _A1(val to: To) extends AnyVal{
      def a: A = {
        lazy val ai: A = {
          val newA = new A{}

          to and newA
        }
        ai
      }
    }

    implicit class _AInto(val into: Into) extends AnyVal{
      def a: A = {
        lazy val ai: A = {
          val newA = new A{}

          into and newA
        }
        ai
      }
    }


    implicit class _A2(val transforms: Transforms) extends AnyVal{
      def a: A = {
        lazy val ai: A = {
          val newA = new A{}

          transforms and newA
        }
        ai
      }
    }
  }

  object Task {
    implicit class _Task(val a: A) extends AnyVal{

      def task: Task = {
        lazy val taski: Task = {
          val newTask = new Task{}

          a and newTask
        }
        taski
      }
    }

  }

  object Create {
    implicit class _Create(val can: Can) extends AnyVal{

      def create: Create = {
        lazy val createi: Create = {
          val newCreate = new Create{}

          can and newCreate
        }
        createi
      }
    }

  }
  


  object Can {
    implicit class _Can(val entity: Entity) extends AnyVal{

      def can: Can = {
        lazy val cani: Can = {
          val newCan = new Can{}

          entity and newCan
        }
        cani
      }
    }

  }
  
  object Transforms {
    implicit class _Transforms(val entity: Entity) extends AnyVal{

      def transforms: Transforms = {
        lazy val transi: Transforms = {
          val newTransforms = new Transforms{}

          entity and newTransforms
        }
        transi
      }
    }

  }
  
  
  trait TodoList extends List

  object TodoList {
    implicit class _TodoList(val a: A) extends AnyVal{

      def todoList: TodoList = {
        lazy val todoli: TodoList = {
          val newTodoList = new TodoList{}

          a and newTodoList
        }
        todoli
      }
    }

  }


  trait To extends Term

  object To {
    implicit class _To(val list: List) extends AnyVal{

      def to: To = {
        lazy val toi: To = {
          val newTo = new To{}

          list and newTo
        }
        toi
      }
    }

  }

  trait Into extends Term
  object Into {
    implicit class _Into(val list: List) extends AnyVal{

      def into: Into = {
        lazy val intoi: Into = {
          val newInto = new Into{}

          list and newInto
        }
        intoi
      }
    }

  }



  object Schedule {
    implicit class _To(val a: A) extends AnyVal{

      def schedule: Schedule = {
        lazy val schedi: Schedule = {
          val newSchedule = new Schedule{}

          a and newSchedule
        }
        schedi
      }
    }

  }

}

//The purpose of the salesman level is to declare the capacities of the app and using these capacities without performing them.
//In order to do that, we need to be able to get the action from the actionable type (e.g. creatable should get the creating function from the creatble type itself)











