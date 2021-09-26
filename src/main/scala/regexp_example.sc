val value = "QueryResult{status='success', finalSuccess=true, parseSuccess=true, allRows=[{\"cbcnt\":0}], signature={\"cbcnt\":\"number\"}, info=N1qlMetrics{resultCount=1, errorCount=0, warningCount=0, mutationCount=0, sortCount=0, resultSize=11, elapsedTime='5.080179ms', executionTime='4.931124ms'}, profileInfo={}, errors=[], requestId='754d19f6-7ec1-4609-bf2a-54214d06c57c', clientContextId='542bc4c8-1a56-4afb-8c2f-63d81e681cb4'}   |"
val regex = """"cbcnt":(\d+)""".r.unanchored
val s"${regex(result)}" = value

println(result)