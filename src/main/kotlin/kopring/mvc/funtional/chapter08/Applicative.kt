package kopring.mvc.funtional.chapter08

import kopring.mvc.funtional.chapter07.Functor

interface Applicative<out A> : Functor<A> {

    fun <V> pure(value: V): Applicative<V>

    infix fun <B> apply(ff: Applicative<(A) -> B>): Applicative<B>
}