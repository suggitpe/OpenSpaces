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
4. In eclipse test that you can deploy the hello world app by selecting the project (right click) and select "run as..." -> android application.  This should interact with your emulator that you created earlier and open up teh hello world application.

Build the application
1. You should now have an application that is alterable.  Open up the res/layout/main.xml file and it shoudl open up the layout designr in eclipse.  Use this to add buttons etc to the application view.  For each of the things you have added, right click and select edit ID and give them each a sensible name that the application code can refer to.
2. In the main Activity class (see src/<package>/HelloWorld.java) you now need to create references to these widgets using the following syntax to get them by ID:
    Button button = (Button) findViewById(R.id.<button id>);
3.  Give the button an OnClickListener so that you can define the behaviour when clicked
4. Compile and test it
You now have a running an working example of an Android Application.

Things to do now:
 - add a status 'Toast'
 - test the application
 - convert it to a maven build
 - have a store of data internally (so it retains state between phone restarts)
 - create a context menu to change the text
 - create a normal menu item to change the text
 - get it to upload the data to a web based store
 - add an advert
 - create some animation for the text



