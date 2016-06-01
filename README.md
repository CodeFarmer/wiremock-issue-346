# wiremock-issue-346
An attempt to replicate the issue described in Wiremock issue #347 from scratch

Test files generated like this:

$ for i in {1..200} ; do perl -sp -e "s/CLASSNAME/Test${i}/" < Test.java.template > src/test/java/org/codefarmer/wiremock/Test${i}.java ; done

