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

/**
  *
  * Created by yoav on 9/14/17.
  */
object DoctorsAppointmentApp extends Entity with App {

  val sentence = I.would.like.to.schedule.a.doctor.s.appointment

  println(sentence.terms.reverse)
  interpretate(sentence.terms.reverse.toList)

  type Sentence = scala.collection.immutable.List[Term]
  import scala.collection.mutable.HashMap

  trait Meaning{
    var isComplete: Boolean
    var completionQuestions: Sentence

    override def toString: String = completionQuestions.mkString(" ")
  }

  def interpretate(terms: scala.collection.immutable.List[Term]) = {
    def getMeaning(terms: Term*): Option[Meaning] = {
      if (meanings.contains(terms.toList)){
        Some(meanings(terms.toList))
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
          println(meaning)
          (a1._1 ++ a2._1, meaning)
      }
    }
  }

  val meanings = HashMap[Sentence, Meaning]()
  meanings(List(I)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = You.what.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = You.would.what.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would, Like)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = You.would.like.to.what.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would, Like, To)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = You.would.like.to.what.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would, Like, To, Schedule)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = You.would.like.to.schedule.what.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would, Like, To, Schedule, A)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = You.would.like.to.schedule.a.what.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would, Like, To, Schedule, A, Doctor)) = new Meaning {
    override var isComplete: Boolean = false
    override var completionQuestions: Sentence = What.`do`.you.mean.asQuestion.terms.reverse.toList
  }

  meanings(List(I, Would, Like, To, Schedule, A, Doctor, Appointment)) = new Meaning {
    override var isComplete: Boolean = true
    override var completionQuestions: Sentence = What.`do`.you.mean.asQuestion.terms.reverse.toList
  }

}