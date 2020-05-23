package monad;

import function.Function3;
import function.Function4;
import scala.concurrent.Future;
import scala.util.Either;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MonadFutEitherWrapper<E, T> {

    private final MonadFutEither<E> m;

    private final Future<Either<E, T>> fut;

    private MonadFutEitherWrapper(Future<Either<E, T>> fut, MonadFutEither<E> m) {
        this.m = m;
        this.fut = fut;
    }

    public static <E,T> MonadFutEitherWrapper<E, T> wrap(Future<Either<E, T>> fut, MonadFutEither<E> m) {

        return new MonadFutEitherWrapper<>(fut, m);
    }

    public Future<Either<E, T>> value() {
        return fut;
    }

    public <S> MonadFutEitherWrapper<E, S> flatMap( Function<T, Future<Either<E,S>>> f ){

        return wrap( m.flatMap(fut, f), m);
    }

    public <S> MonadFutEitherWrapper<E, S> map( Function<T, S> f ){

        return wrap( m.map(fut, f), m);
    }

    public MonadFutEitherWrapper<E,T> handleErrorWith( Function<E, Future<Either<E,T>>> f ) {

        return wrap( m.handleErrorWith(fut, f), m);
    }

    public MonadFutEitherWrapper<E,T> handleError( Function<E, T> f ) {

        return wrap( m.handleError(fut, f), m );
    }

    public <B, S> MonadFutEitherWrapper<E,S> map2( Future<Either<E, B>> fromB, BiFunction<T,B,S> f  ) {

        return wrap( m.map2(fut, fromB, f), m );
    }

    public <B,C,S> MonadFutEitherWrapper<E,S> map3( Future<Either<E, B>> fromB,
                                                         Future<Either<E, C>> fromC,
                                                         Function3<T,B,C,S> f  ) {

        return wrap( m.map3(fut, fromB, fromC, f), m );
    }

    public <A, B, C, S> MonadFutEitherWrapper<E,S> map4(Future<Either<E, A>> fromA,
                                                        Future<Either<E, B>> fromB,
                                                        Future<Either<E, C>> fromC,
                                                        Function4<T, A, B, C, S> f) {
        return wrap( m.map4(fut, fromA, fromB, fromC, f), m );
    }
}
