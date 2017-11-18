package org.communitee.talk

/**
  * Created by root on 30/10/17.
  */
package object meanings {
  trait Meaning

  trait Label

  trait Scene

  object Labeless extends Label

  trait Wishabale extends Label

  trait Person extends Label

  object Speaker extends Person

  trait Action extends Label
  object Action extends Action

  trait State extends Label

  trait Time extends Label

  trait Future extends Time
  object Future extends Future

  object Past extends Time

  object Present extends Time

  object FutureAction extends Action with Future

  object FutureState extends State with Future

  trait Sentiment extends Label

  object Wish extends Sentiment

  object Like extends Sentiment

  object Similarity extends Label

  object ActionName extends Label

  case class To()

  trait Profession extends Label

  trait Professional extends Label with Profession

  trait Medicine extends Profession

  object Doctor extends Professional with Medicine

  trait Place extends Label

  trait Meeting extends Label

  trait Appointment extends Meeting with Time with Place

  object Appointment extends Appointment

  trait SentimentRegardingAScene extends Meaning{
    val sentiment: Sentiment
    val scene: Scene
  }
}