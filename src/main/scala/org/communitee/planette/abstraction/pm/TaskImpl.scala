package org.communitee.planette.abstraction.pm

import org.communitee.planette.abstraction.A

/**
  * Created by root on 02/08/17.
  */
object TaskImpl {
  implicit class _Task(val a: A) extends AnyVal{

    def task: Task = {
      lazy val taski: Task = {
        val newTask = ??? //Task()

        a and newTask
      }
      taski
    }
  }

}