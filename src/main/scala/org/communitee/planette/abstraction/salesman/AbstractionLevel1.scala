package org.communitee.planette.abstraction.salesman

import org.communitee.planette.domain.{AbstractOperations, CommonOperations}

/**
  * Created by yoav on 5/5/17.
  */
trait AbstractionLevel1 extends CommonOperations with AbstractionLevel1Aggregate {

  def createRoutine: IOperation[InfoForRoutineCreation, Routine]

  def updateRoutine: IOperation[InfoForRoutineUpdate, Routine]

  def deleteRoutine: IOperation[InfoForRoutineDeletion, Routine]

  def runRoutine[Container[Task]]: IOperation[Container[Task], RoutineOutcomeIfAny]

  def createTask: IOperation[InfoForTaskCreation, Task]

  def updateTask: IOperation[InfoForTaskUpdate, Task]

  def deleteTask: IOperation[InfoForTaskDeletion, Task]

  def performTask: IOperation[Task, TaskOutcome]

  def getSchedule: IOperation[List[Task], Schedule]

  def getScheduleDescription: IOperation[Schedule, ScheduleDescription[Schedule]]

  def getScheduleExplanation: IOperation[Schedule, ScheduleExplanation[Schedule]]

  def createEvent: IOperation[InfoForEventCreation, Event]

  def registerToEvent: IOperation[InfoForEventRegistration, EventRegistration]

  def getEventRegistrations: IOperation[Event, List[EventRegistration]]

  def fireEvent: IOperation[Event, EventOutcomeIfAny]

  def getUserURL: IOperation[EventRegistration, UserURL]

  def createEventNotification[E <: Event]: IOperation[E, Notification[E]]

  def notifyUser[E <: Event]: VoidOperation[(UserURL, Notification[E])]

  def askUserFor[A]: ParamlessOperation[A]

}

trait AbstractionLevel1Aggregate {
  type Task
  type InfoForTaskCreation
  type InfoForTaskUpdate

  type InfoForTaskDeletion
  type TaskOutcome
  type TaskFailureOutcome <: TaskOutcome

  type Routine
  type RoutineOutcomeIfAny
  type InfoForRoutineUpdate
  type InfoForRoutineCreation
  type InfoForRoutineDeletion

  type Event
  type EventOutcomeIfAny
  type InfoForEventRegistration
  type InfoForEventCreation
  type EventRegistration

  type TaskFailedEvent <: Event

  type Notification[E <: Event]
  type UserURL

  type Person
  type Schedule
  type ScheduleDescription[Schedule]
  type ScheduleExplanation[Schedule]
}

trait AbstractionLevel1BluePrint extends AbstractionLevel1 with AbstractOperations {


  override type InfoForRoutineCreation = M[Task]

  //The app has Tasks; A task can be performed by a routine or on demand
  def createTaskFlow = {
    val createdTasks = for {
      taskInfo <- askUserFor[InfoForTaskCreation]()
      task <- createTask(taskInfo)
    } yield task

    createdTasks
  }

  //The app has routines it executes on a schedule basis
  def createRoutineFlow = {
    val createdRoutines = for {
      routine <- createRoutine(createTaskFlow)
    } yield routine

    createdRoutines
  }

  // The user can register to different types of events
  def registerToEventFlow = {
    val createdRegistration = for{
      eventInfo <- askUserFor[InfoForEventRegistration]()
      registration <- registerToEvent(eventInfo)
    } yield registration

    createdRegistration
  }

  // The app fires events

  //The app notifies the user of events he wants/should be updated with

  //The app reminds the user of things he wants/should be reminded of

  //The app can converse with user <-> the user can converse with the app
}