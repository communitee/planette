package org.communitee.talk
package apps

import terms._
import WouldImpl._
import LikeImpl._
import ToImpl._
import DoImpl._
import ScheduleImpl._
import AImpl._
import DoctorImpl._
import AppointmentImpl._
import WhatImpl._
import YouImpl._
import MeanImpl._
import org.communitee.talk.labels.{Action, Labeless, Person}
import org.communitee.talk.meanings.{Scene, Sentiment, SentimentRegarding}


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
        SentimentRegarding[Scene](sentiment, new Scene {
          override val events: scala.List[meanings.Event] = Nil
        })
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

