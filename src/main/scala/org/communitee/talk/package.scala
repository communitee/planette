package org.communitee

import java.util.concurrent.atomic.AtomicReference

/**
  * Created by yoav on 9/14/17.
  */
package object talk {
  trait Term {
    //val terms: scala.collection.mutable.MutableList[Term] = scala.collection.mutable.MutableList(this)
    def getTerms:scala.collection.immutable.List[Term] = {
      def getTermsInternal(list: scala.collection.immutable.List[Option[Term]] = List(Some(this))): scala.collection.immutable.List[Option[Term]] = {
        list.head match {
          case None =>
            list.drop(1)
          case Some(p) =>
            getTermsInternal(List(p.pred.get) ::: list)
        }
      }

      getTermsInternal().map(_.get)
    }

    val pred = new AtomicReference[Option[Term]](None)
    def and[B <: Term](a: B): B = {
      //a.terms ++= this.terms
      a.pred.set(Some(this))
      a
    }

    override def toString: String = {
      val simpleName = this.getClass.getSimpleName
      if(simpleName.contains("anon"))
        this.getClass.getInterfaces()(0).getSimpleName
      else
        simpleName.replace("$", "")

    }

    def ? = {
      val result = this and QuestionMark //-- Terms are stateful - mutable --> very bad!!!!!!!!!

      result
    }
  }

  trait QuestionMark extends Term{
    override def toString: String = "?"
  }

  object QuestionMark extends QuestionMark

  trait Thing extends Term

  trait A extends Term
  object A extends A
  
  object AImpl {
    implicit class _A(val schedule: Schedule) extends Term with A {
      def a: A = {
        lazy val ai: A = {
          val  newA = new A{}
          schedule and newA
        }
        ai
      }

    }
  }


  trait Piece extends Term {
    def piece: Piece = ???
  }

  trait Work extends Term

  trait Possesable extends Term

  trait Relation extends Term

  trait Membership extends Relation

  trait Of extends Term {

    def of: Of = ???
  }

  trait Mean extends Term

  object Mean extends Mean

  object MeanImpl {
    implicit class _Mean1(val you: You) extends Term with Mean {
      def mean: Mean = {
        lazy val meani: Mean = {
          val  newMean = new Mean{}
          you and newMean
        }
        meani
      }
    }
  }


  trait You extends Term

  object You extends You

  object YouImpl {
    implicit class _You1(val `do`: Do) extends Term with You {
      def you: You = {
        lazy val youi: You = {
          val  newYou = new You{}
          `do` and newYou
        }
        youi
      }
    }
  }
  
  trait I extends Term

  object I extends I

  trait Would extends Term

  object Would extends Would

  object WouldImpl {
    implicit class _Would(val i: I) extends Term with Would {
      def would: Would = {
        lazy val wouldi: Would = {
          val  newWould = new Would{}
          i and newWould
        }
        wouldi
      }

    }

    implicit class _Would1(val you: You) extends Term with Would {
      def would: Would = {
        lazy val wouldi: Would = {
          val  newWould = new Would{}
          you and newWould
        }
        wouldi
      }

    }
  }

  trait What extends Term

  object What extends What

  object WhatImpl {
    implicit class _What(val you: You) extends Term with What {
      def what: What = {
        lazy val whati: What = {
          val  newWhat = new What{}
          you and newWhat
        }
        whati
      }

    }

    implicit class _What1(val would: Would) extends Term with What {
      def what: What = {
        lazy val whati: What = {
          val  newWhat = new What{}
          would and newWhat
        }
        whati
      }

    }

    implicit class _What2(val to: To) extends Term with What {
      def what: What = {
        lazy val whati: What = {
          val  newWhat = new What{}
          to and newWhat
        }
        whati
      }
    }

    implicit class _What3(val schedule: Schedule) extends Term with What {
      def what: What = {
        lazy val whati: What = {
          val  newWhat = new What{}
          schedule and newWhat
        }
        whati
      }

    }

    implicit class _What4(val a: A) extends Term with What {
      def what: What = {
        lazy val whati: What = {
          val  newWhat = new What{}
          a and newWhat
        }
        whati
      }

    }
  }
  
  trait Like extends Term
  object Like extends Like

  object LikeImpl {
    implicit class _Like(val would: Would) extends Term with Like {
      def like: Like = {
        lazy val likei: Like = {
          val  newLike = new Like{}
          would and newLike
        }
        likei
      }
    }
  }


  trait Doctor extends Term{
    def s : Doctor = this
  }
  
  object Doctor extends Doctor

  object DoctorImpl {
    implicit class _Doctor(val a: A) extends Term with Doctor {
      def doctor: Doctor = {
        lazy val doctori: Doctor = {
          val  newDoctor = new Doctor{}
          a and newDoctor
        }
        doctori
      }

    }
  }
  
  trait Appointment extends Term
  object Appointment extends Appointment
  
  object AppointmentImpl {
    implicit class _Appointment(val doctor: Doctor) extends Term with Appointment {
      def appointment: Appointment = {
        lazy val appointmenti: Appointment = {
          val  newAppointment = new Appointment{}
          doctor and newAppointment
        }
        appointmenti
      }

    }
  }
  
  trait Task extends Term

  trait Create extends Term

  trait Entity extends Term

  trait Can extends Term

  trait Location extends Term

  trait Duration extends Term

  trait Description extends Term

  trait Transform extends Term

  trait Transforms extends Transform

  trait List extends Term

  trait TodoList extends List

  trait To extends Term
  
  object To extends To

  object ToImpl {
    implicit class _To(val like: Like) extends Term with To {
      def to: To = {
        lazy val toi: To = {
          val  newTo = new To{}
          like and newTo
        }
        toi
      }
    }
  }

  trait Do extends Term

  object Do extends Do

  object DoImpl {
    implicit class _Do(val what: What) extends Term with Do {
      def `do`: Do = {
        lazy val toi: Do = {
          val  newDo = new Do{}
          what and newDo
        }
        toi
      }
    }
  }


  trait Schedule extends Term
  object Schedule extends Schedule

  object ScheduleImpl {
    implicit class _Schedule(val to: To) extends Term with Schedule {
      def schedule: Schedule = {
        lazy val schedulei: Schedule = {
          val  newSchedule = new Schedule{}
          to and newSchedule
        }
        schedulei
      }

    }
  }

}
