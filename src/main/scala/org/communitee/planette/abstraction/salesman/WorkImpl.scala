package org.communitee.planette.abstraction
package salesman


/**
  * Created by root on 21/07/17.
  */
object WorkImpl{
  implicit class _Work(val of: Of) extends Term with Work {
     def work: Work = {
      lazy val worki: Work = {
        val  newWork = new Work{}
        of and newWork
      }
      worki
    }


  }

}
