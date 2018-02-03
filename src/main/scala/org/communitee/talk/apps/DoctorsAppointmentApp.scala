package org.communitee.talk
package apps

import org.communitee.talk.labels._
import org.communitee.talk.meanings.{Person => _, _}
import org.communitee.talk.terms.AImpl._
import org.communitee.talk.terms.AppointmentImpl._
import org.communitee.talk.terms.DoctorImpl._
import org.communitee.talk.terms.LikeImpl._
import org.communitee.talk.terms.ScheduleImpl._
import org.communitee.talk.terms.ToImpl._
import org.communitee.talk.terms.WouldImpl._
import org.communitee.talk.terms._


/**
  *
  * Created by yoav on 9/14/17.
  */
object DoctorsAppointmentApp extends Entity with App {

  val sentence = I.would.like.to.schedule.a.doctor.s.appointment
  println(sentence.getTerms)
  val labels = sentence.getTerms.map(_.processLabels)
  val cleanLabels = labels.filter(_.isDefined).map(_.get).filterNot(_ == Labeless)
  println(cleanLabels.mkString(" "))
  val meaning = Kernel.getTransmissionMeaning(sentence.getTerms)
  val i =0
  type ImmutableList[B] = scala.collection.immutable.List[B]
  type Sentence = ImmutableList[Term]


}

object Kernel{

  import org.communitee.talk.meanings.Meaning

  def getTransmissionMeaning(transmission: Transmission): Option[Meaning] = {
//List(d:Person, f:Sentiment, a:Action) :: rest
    val labels = transmission.map(_.processLabels).filter(_.isDefined).map(_.get).filterNot(_ == Labeless)
    labels match {
      case (person:Person) :: (sentiment:Sentiment) :: (action:Action) :: rest =>
        println(s"person: $person, sentiment: $sentiment, action: $action")
        println(s"the rest: $rest")
        action match {
          case a: StateProducingAction =>
            a.getState(rest.map(_.toMeaning).filter(_.isDefined).map(_.get)) match {
              case Some(state) => SentimentRegarding[State] (sentiment, state)
              case None => None
            }
          case b: SceneProducingAction =>
            SentimentRegarding[Scene](sentiment, new Scene {
              override val events: scala.List[meanings.Event] = Nil
            })
        }

        None
      case other =>
        println(other)
        None
    }
  }

  def getInternalReaction(meaning: Meaning): Option[Reaction] = None

  def getCourseOfAction(meaning: Meaning): Option[CourseOfAction] = None

  def executeCourseOfAction(courseOfAction: CourseOfAction): Option[Outcome] = None

  def comp(transmission: Transmission) = for{
    meaning <- getTransmissionMeaning(transmission)
    course <- getCourseOfAction(meaning)
    outcome <- executeCourseOfAction(course)
  } yield (course, outcome)
}

