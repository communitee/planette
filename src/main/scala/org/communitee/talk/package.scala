package org.communitee

/**
  * Created by yoav on 9/14/17.
  */
package object talk {
  trait Term {
    val terms: scala.collection.mutable.MutableList[Term] = scala.collection.mutable.MutableList(this)
    val neededInoformation: scala.collection.immutable.List[Term] = List.empty[Term]

    def and[B <: Term](a: B): B = {
      a.terms ++= this.terms
      a
    }

    override def toString: String = {
      val simpleName = this.getClass.getSimpleName
      if(simpleName.contains("anon"))
        this.getClass.getInterfaces()(0).getSimpleName
      else
        simpleName.replace("$", "")

    }
  }

  trait Thing extends Term

  trait A extends Term

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
  
  trait I extends Term

  object I extends I

  trait Would extends Term

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
  }
  
  trait Like extends Term

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


  trait Doctor extends Term

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


  trait Schedule extends Term

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
