package org.communitee.talk

import org.communitee.talk.meanings._

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

    def toMeaning: Option[Meaning] = None
  }
  trait Location extends Label
  object Labeless extends Label

  trait Wishabale extends Label

  trait Person extends Label

  object Speaker extends Person

  object Schedule extends Schedule with Label

  object SchedulingAction extends SchedulingAction with Label
  object SchedulingActionOf extends SchedulingAction with Label


  object Somewhere extends Location


  object Sometime extends Time


  object Future extends Future with Label

  object Past extends Time

  object Present extends Time

  object FutureAction extends Action with Future

  object FutureState extends State with Future

  //trait Sentiment extends Label

  object Wish extends Sentiment with Label

  object Like extends Sentiment

  object Similarity extends Label

  object Execute extends Label

  object Doctor extends meanings.Doctor with Label{
    override def toMeaning = Some(this.asInstanceOf[meanings.Doctor])
  }

  object Appointment extends Appointment with Label{
    override def toMeaning = Some(this.asInstanceOf[meanings.Appointment])
  }


}
