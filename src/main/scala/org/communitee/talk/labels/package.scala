package org.communitee.talk

/**
  * Created by root on 02/01/18.
  */
package object labels {
  trait Label{
    override def toString: String = {
      val simpleName = this.getClass.getSimpleName
      if(simpleName.contains("anon"))
        this.getClass.getInterfaces()(0).getSimpleName
      else
        simpleName.replace("$", "")
    }
  }
  trait Location extends Label
  object Labeless extends Label

  trait Wishabale extends Label

  trait Person extends Label

  object Speaker extends Person

  trait Action extends Label

  trait Schedule extends Label
  object Schedule extends Schedule
  trait SchedulingAction extends Schedule with Action
  object SchedulingAction extends SchedulingAction
  trait State extends Label

  object Somewhere extends Location

  trait Time extends Label
  object Sometime extends Time

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

  object Execute extends Label

  trait Profession extends Label

  trait Professional extends Label with Profession

  trait Medicine extends Profession

  object Doctor extends Professional with Medicine

  trait Place extends Label

  trait Meeting extends Label

  trait Appointment extends Meeting with Time with Place

  object Appointment extends Appointment


}
