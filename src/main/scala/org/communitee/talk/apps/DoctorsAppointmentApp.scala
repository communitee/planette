package org.communitee.talk
package apps

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
import org.communitee.talk.apps.DoctorsAppointmentApp.Sentence

import scala.List

/**
  *
  * Created by yoav on 9/14/17.
  */
object DoctorsAppointmentApp extends Entity with App {

  val sentence = I.would.like.to.schedule.a.doctor.s.appointment
  import M._
  println(sentence.getTerms)
  val meaning = interpretate(sentence.getTerms)
  println(meaning)

  type Sentence = scala.collection.immutable.List[Term]


  trait Meaning{
    var isComplete: Boolean
    val completionQuestions: Option[Sentence] = None
    val terms: Option[Sentence] = None
    override def toString: String = completionQuestions.mkString(" ")
  }

  def interpretate(terms: scala.collection.immutable.List[Term]) = {
    def getMeaning(terms: Term*): Option[Meaning] = {
      val sentence = terms.toList.mkString(" ")
      if (meanings.contains(sentence)){
        Some(meanings(sentence))
      }else{
        None
      }
    }

    def mapper(t: Term)=Tuple2[Seq[Term], Option[Meaning]](Seq[Term](t), None)

    terms.map(mapper).fold((Seq(), None)){(a1,a2) =>
      getMeaning((a1._1 ++ a2._1): _*) match{
        case None =>
          (a1._1 ++ a2._1, None)
        case meaning: Option[Meaning] =>
          (a1._1 ++ a2._1, meaning)
      }
    }
  }

}

object M{
  import org.communitee.talk.apps.DoctorsAppointmentApp.Meaning
  type Sentence = scala.collection.immutable.List[Term]
  import scala.collection.mutable.HashMap
  val meanings = HashMap[String, Meaning]()
  meanings(List(I).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(You.what.?.getTerms)
  }

  meanings(List(I, Would).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(You.would.what.?.getTerms)
  }

  meanings(List(I, Would, Like).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(You.would.like.to.what.?.getTerms)
  }

  meanings(List(I, Would, Like, To).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(You.would.like.to.what.?.getTerms)
  }

  meanings(List(I, Would, Like, To, Schedule).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(You.would.like.to.schedule.what.?.getTerms)
  }

  meanings(List(I, Would, Like, To, Schedule, A).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(You.would.like.to.schedule.a.what.?.getTerms)
  }

  meanings(List(I, Would, Like, To, Schedule, A, Doctor).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = false
    override val completionQuestions: Option[Sentence] = Some(What.`do`.you.mean.?.getTerms)
  }

  meanings(List(I, Would, Like, To, Schedule, A, Doctor, Appointment).mkString(" ")) = new Meaning {
    override var isComplete: Boolean = true
    //override val terms: Option[Sentence] = Some()

  }
}