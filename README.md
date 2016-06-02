# wiremock-issue-346
An attempt to replicate the issue described in Wiremock issue #347 from scratch

(See https://github.com/tomakehurst/wiremock/issues/346)

Test files generated like this:

$ for i in {2..1000} ; do perl -sp -e "s/Test1/Test${i}/" < src/test/java/org/codefarmer/wiremock/Test1.java > src/test/java/org/codefarmer/wiremock/Test${i}.java ; done

So far, zero success replicating the problem with a thousand test cases.