# map-flatMap-training
Some useful tests to understand map and flatMap operations over functors and monads

## Introduction
This project contains a test class (`MapVsFlatMapTest`) that contains a set of tests to understand the behaviour of map, flatMap and the how thery are related.

It uses several types to work on: Optional, List and Futures, just to show that no matter the type your working on, map and flatMap operations do the same thing: a transformation.

This project also contains a test class to go over how to combine futures: `CombineRuturesTest`.

And what's more it contains another test class (`WorkWithFuturesUsingMonadTransormerTest`) to get used to the same operations but instead of using raw Scala futures, using the MonadTransformer object.

Finally there is a practical exercise to use the monad transformer to combine several futures to get the final result: `SummaryServiceTest` and `SummaryServiceImpl`.

# How to start
To start solving the tests, just download master branch and execute tests (using your IDE or mvn test).

If you have trouble solving a test and you're about to give up, you have all the tests solved inside the branch `solved`.

Good look!!
