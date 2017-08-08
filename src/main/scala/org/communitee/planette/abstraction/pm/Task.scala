package org.communitee.planette.abstraction
package pm

import org.communitee.planette.abstraction.{Task => AbstractTask}
/**
  * Created by root on 01/08/17.
  */
case class Task(description: Description, location: Location, duration: Duration) extends AbstractTask{

}
