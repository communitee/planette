package org.communitee.planette.abstraction

/**
  * Created by root on 01/08/17.
  */
package object pm {
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
  }



  object Create {

    implicit class _Create(val can: Can) extends AnyVal {

      def create: Create = {
        lazy val createi: Create = {
          val newCreate = new Create {}

          can and newCreate
        }
        createi
      }
    }

  }
}
