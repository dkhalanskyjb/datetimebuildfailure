# datetimebuildfailure
A simple app to illustrate the
Invalid record (Producer: 'LLVM8.0.0svn' Reader: 'LLVM APPLE_1_1200.0.32.21_0') #63

issue on the kotlinx-datetime library

added some useful and standard multiplatorm dependencies and plugins and boom

The android app will compile and run, but after you add a LocalDateTime field to a model
the ios won't compile giving an
Invalid record (Producer: 'LLVM8.0.0svn' Reader: 'LLVM APPLE_1_1200.0.32.21_0'
error as the reason# datetimebuildfailure
