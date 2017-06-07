package org.communitee.planette

/**
  * Created by yoav on 5/5/17.
  */
package object domain {
  trait CommonOperations {
    type M[T] <: {
      def map[S](f: T => S): M[S]

      def flatMap[S](f: T => M[S]): M[S]

      def get(): T
    }
    type IOperation[A, B] = A => M[B]
    type ParamlessOperation[A] = () => M[A]
    type VoidOperation[A] = M[A] => M[Unit]
  }

  trait AuthenticatedOperations extends CommonOperations{
    type AuthenticationToken
    type AuthenticatedIOperation[A,B] = AuthenticationToken => IOperation[A,B]
    type AuthenticatedOperation[B] = AuthenticationToken => ParamlessOperation[B]
  }

  trait Monadic[T] {
    type M[S]
    def map[S](f: T => S): M[S]

    def flatMap[S](f: T => M[S]): M[S]

    def get(): T
  }

  case class M1[T](value: T) extends Monadic[T] {

    override def flatMap[S](f: (T) => M1[S]): M1[S] = {
      f(value)
    }

    override type M[S] = M1[S]

    override def map[S](f: (T) => S): M1[S] = {
      M1(f(value))
    }

    override def get(): T = value
  }

  trait AbstractOperations extends AuthenticatedOperations{

    override type M[S] = M1[S]
    override type AuthenticationToken = String
    //override type IOperation[A,B] = A => M1[B]
    //override type Operation[A] = Unit => M1[A]
  }

  def mock[A]: A = ???
}
