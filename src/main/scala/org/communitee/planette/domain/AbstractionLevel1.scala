package org.communitee.planette.domain

/**
  * Created by yoav on 5/5/17.
  */
trait AbstractionLevel1 extends CommonOperations with AbstractionLevel1Aggregate {
  def createRoutine: IOperation[InfoForRoutineCreation, Routine]

  def updateRoutine: IOperation[InfoForRoutineUpdate, Routine]

  def deleteRoutine: IOperation[InfoForRoutineDeletion, Routine]

  def createTask: IOperation[InfoForTaskCreation, Task]

  def updateTask: IOperation[InfoForTaskUpdate, Task]

  def deleteTask: IOperation[InfoForTaskDeletion, Task]

  def makeTaskFailureEvent: IOperation[TaskFailureOutcome, Event]

  def processTask: IOperation[Task,TaskOutcome]

  def performTask: IOperation[Task, TaskOutcome] = { task => {
    val outcome = processTask(task)
    outcome.map { o =>
      o match {
        case failure: TaskFailureOutcome =>
          for {
            event <- makeTaskFailureEvent(failure)
            eventOutcome <- fireEvent(event)
          } yield eventOutcome
      }
    }
    outcome
  }
  }

  def createEvent: IOperation[InfoForEventCreation, Event]

  def registerToEvent: IOperation[InfoForEventUpdate, EventRegistration]

  def getEventRegistrations: IOperation[Event, List[EventRegistration]]

  def fireEvent: IOperation[Event, EventOutcome]

  def createEventNotification[E <: Event]: IOperation[E, Notification[E]]

  def notifyUser[E <: Event]: VoidOperation[Notification[E]]

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
  type RoutineOutcome
  type InfoForRoutineUpdate
  type InfoForRoutineCreation
  type InfoForRoutineDeletion

  type Event
  type EventOutcome
  type InfoForEventUpdate
  type InfoForEventCreation
  type EventRegistration

  type TaskFailedEvent <: Event

  type Notification[E <: Event]

  type Person
  type Schedule
}

trait AbstractionLevel1BluePrint extends AbstractionLevel1 with AbstractOperations {

  def transform[A, B](a: A): M[B]

  override type InfoForRoutineCreation = M[Task]

  //The app has Tasks; A task can be performed by a routine or on demand
  def createTaskFlow = {
    val result = for {
      taskInfo <- askUserFor[InfoForTaskCreation]()
      task <- createTask(taskInfo)
    } yield task

    result
  }

  //The app has routines it executes on a schedule basis
  def createRoutineFlow(tasks: M[Task]) = {
    val result = for {
      routine <- createRoutine(createTaskFlow)
    } yield routine

    result
  }

  //The app fires events

  //The app notifies the user of events he wants/should be updated with
  //The app reminds the user of things he wants/should be reminded of
  //The app can conversate with user <-> the user can conversate with the app
}