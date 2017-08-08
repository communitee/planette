package org.communitee.planette.abstraction
package salesman


/**
  * Created by root on 21/07/17.
  */
object PieceImpl {
  implicit class _Piece(val v: A) extends AnyVal{

    def piece: Piece = {
      lazy val piecei: Piece = {
        val newPiece = new Piece{}

        v and newPiece
      }
      piecei
    }
  }

}
