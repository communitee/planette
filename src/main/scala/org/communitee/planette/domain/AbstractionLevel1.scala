package org.communitee.planette.domain

/**
  * Created by yoav on 5/5/17.
  */
trait AbstractionLevel1 extends CommonOperations with AbstractionLevel1Aggregate {
  def createRoutine: IOperation[InfoForRoutineCreation, Routine]

  def createTask: IOperation[InfoForTaskCreation, Task]

  def performTask: IOperation[Task, TaskOutcome]
}

trait AbstractionLevel1Aggregate {
  type Person
  type Task
  type InfoForTaskCreation
  type TaskOutcome
  type Schedule
  type Routine
  type InfoForRoutineCreation
}

trait AbstractionLevel1BluePrint extends AbstractionLevel1 with AbstractOperations {
  //The app has routines it executes on a schedule basis
  //The app has Tasks; A task can be performed by a routine or on demand
  //The app notifies the user of events he wants/should be updated with
  //The app reminds the user of things he wants/should be reminded of
  //The app can conversate with user <-> the user can conversate with the app
}