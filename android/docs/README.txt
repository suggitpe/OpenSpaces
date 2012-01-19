Do the following to replicate what we did in the OpenSpace.

Set up
1. Download & install Java 1.6 JDK (set up env vars etc)
2. Download & install the Android SDK (set env vars etc)
3. Now open up the Android SDK Manager ... this is important as it allows you to download the right env to develop against (to ensure backward compatability use 2.2).  Also in DDMS create the emulator environment for 2.2.
4. Install Eclipse
5. install Eclipse Android stuff (install software from [https://dl-ssl.google.com/android/eclipse/])


Create project
1. Create a project using the Android SDK 
    [android create project --package org.openspaces.android.helloworld --activity HelloWorld --target 2 --path `pwd`/HelloWorld]
    - use [android list targets] to see the various targets
2. This is the bit where I would  normally convert it into a maven project but MC told me not to do it today :-(
3. Import project into Eclipse using DDMS plugin ... switch to DDMS view and then create new project (File --> New)
    - select create Android project
    - select the directory where you created the android project
4 write the application.



