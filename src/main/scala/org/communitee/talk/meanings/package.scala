package org.communitee.talk



/**
  * Created by root on 30/10/17.
  */
package object meanings {
  trait Meaning




  trait Event extends Meaning{
    type Happening
    val happening: Happening
    val location: Location
    val time: Time
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

  case class SpeakerSentimentForAScene(speaker: Person, sentiment: Sentiment, scene: Scene)

  trait Time extends Meaning
  trait AbstractTime extends Time
  object Sometime extends AbstractTime
  object Future extends AbstractTime

  trait Location extends Meaning
  trait AbstractLocation extends Location
  object Somewhere extends AbstractLocation

  trait Person extends Meaning
  trait Sentiment extends Meaning
}