# Java coding problem tips

## Input

```java
// 1. Open stdin as BufferedReader
var br = new BufferedReader(new InputStreamReader(System.in));

// 2. Parse input with StringTokenizer
var st = new StringTokenizer(br.readLine());

// example: input = `6 7`
var six = Integer.parseInt(st.nextToken());
var seven = Integer.parseInt(st.nextToken());
```

Use `BufferedReader` instead of `Scanner`.

Use `StringTokenizer::nextToken` and parse methods instead of `String::split` method.

## Data

```java
// need to use queue for (x, y, distance) data
class State { /* ... */ }

Queue<State> queue = new LinkedList<>();
queue.add(new State(6, 7, 10));
```

The example above might use too much space in the heap.

```java
Queue<int[]> queue = new LinkedList<>();
queue.add(new int[]{ 6, 7, 10 });
```

For simple data, it might be good idea to use arrays.