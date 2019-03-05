#!/usr/bin/env bash
$query = $'"select * from xref where timestamp > ? limit 20" -Dfrom=\'2019-01-05\''
$JAVA_HOME/java -jar $EXERCISE_HOME\\lib\\QueryExecutor.jar $query