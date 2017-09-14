package org.communitee.talk
package apps

import WouldImpl._
import LikeImpl._
import ToImpl._
import ScheduleImpl._
import AImpl._
import DoctorImpl._
import AppointmentImpl._
/**
  * Created by yoav on 9/14/17.
  */
object DoctorsAppointmentApp extends Entity with App {

  val i: I = new I{}

  val sentence = I.would.like.to.schedule.a.doctor.appointment

  println(sentence.terms.reverse)
}
