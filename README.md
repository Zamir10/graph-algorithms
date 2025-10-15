Project Overview

This repository contains three branches, each focusing on a different stage of graph-related problem-solving and application development.

task/01-graph-processing
This branch includes the implementation for detecting cycles in a graph, along with several unit tests.
To run the tests, execute:

mvn test


task/02-graph-shortest-path
This branch implements an algorithm to find the shortest path in a weighted, directed graph, accompanied by unit tests.
To run the tests, execute:

mvn test


task/03-expose-graph-processing
This branch extends the previous two tasks by adapting the implementations to Spring Boot conventions.
It also includes integration tests for all exposed endpoints.
To run the tests, execute:

mvn test