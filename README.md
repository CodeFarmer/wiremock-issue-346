# wiremock-issue-346
An attempt to replicate the issue described in Wiremock issue #347 from scratch

Test files generated like this:

$ for i in {2..200} ; do perl -sp -e "s/Test1/Test${i}/" < src/test/java/org/codefarmer/wiremock/Test1.java > src/test/java/org/codefarmer/wiremock/Test${i}.java ; done

