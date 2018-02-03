package org.communitee.talk

/**
  * Created by root on 30/10/17.
  */
package object meanings {
  trait Meaning{
    def is[A](a: A): Boolean = false
  }

  trait Event extends Meaning{
    type Happening
    val happening: Happening
    val location: Location
    val time: Time
  }
  trait State extends Meaning
  trait Action extends Meaning
  trait StateProducingAction extends Action{
    def getState(meanings: List[Meaning]): Option[State] =
      None
  }
  trait SceneProducingAction extends Action

  trait SchedulingAction extends StateProducingAction{
    override def getState(meanings: List[Meaning]) = super.getState(meanings)
  }
  trait Scene extends Meaning {
    val events: List[Event]
  }

  trait Scheduler extends Meaning{
    def make[A<:ScheduleRequest](a: A): Schedulable
  }
  trait Schedulable extends Meaning
  trait ScheduleRequest extends Meaning
  
  trait SentimentForAScene extends Meaning{
    val sentiment: Sentiment
    val scene: Scene
  }

  case class SentimentRegarding[A](sentiment: Sentiment, a:A)

  trait Time extends Meaning
  trait AbstractTime extends Time
  object Sometime extends AbstractTime
  trait Future extends AbstractTime

  trait Location extends Meaning
  trait AbstractLocation extends Location

  trait Schedule extends Meaning
  trait Person extends Meaning
  trait Sentiment extends Meaning


  trait Meeting extends Meaning
  trait Place extends Meaning
  trait Appointment extends Meeting with Time with Place{
    override def is[A](a: A) = a match {
      case a_ : Schedulable => true
      case other => false
    }
  }

  trait Profession extends Meaning

  trait Professional extends Meaning with Profession

  trait Medicine extends Profession

  trait Doctor extends Professional with Medicine
}