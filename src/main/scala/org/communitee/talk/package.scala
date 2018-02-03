package org.communitee

import org.communitee.talk.meanings.Meaning
import org.communitee.talk.terms.Term

/**
  * Created by root on 05/11/17.
  */
package object talk {
  type Transmission = List[Term]

  trait CourseOfAction

  trait Reaction{
    val meaning: Meaning
  }

  trait Outcome
}

