package org.communitee.planette.abstraction
package salesman

/**
  * Created by root on 21/07/17.
  */
object OfImpl {
  implicit class _Of[T<:Piece](val v: T) extends Term {
    def of: Of = {
      lazy val ofi: Of = {
        val res = new Of{}
        v and res
      }

      ofi
    }
  }
}
