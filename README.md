# burghard-FeelsBook

Noah Burghardt

Completed as part of 
CMPUT 301 Assignment 1 Fall 2018
at the University of Alberta.
FeelsBook

Deliverables:
  * Complete code base
  * Video: https://youtu.be/-kHgXGtGa38
  * UML diagram -> /doc/diagram
  * apk -> app/build/outputs/apk/burghard-FeelsBook.apk

Usage:
1. Homepage of app you are greeted with six buttons
    * tap on buttons to add an emotion, with a current time stamp
    * the added emotions are presented as cards with the most recent at the top
2. Swipe any card to the left or right to delete it
3. Tap a card to edit the emotion
    * Tap on the time stamp to edit the date
    * Tap on "add comment" to edit the comment
    * Tap the back button or return arrow to return to main screen
4. Tap the hamburger menu button or swipe in from the left to open count drawer.
    * A count of each type of feeling is listed

Acknowledgements:
 - App design was inspired by the Google Keep app and material design standards
 - Model structure based off the one shown in the studentpicker saga
 - Back button in app bar in edit activity
 	- Coding Demos, https://www.codingdemos.com/android-toolbar-back-button/
 - Date/Time picker implementation
 	- Vasily Kabunov, https://stackoverflow.com/questions/2055509/datetime-picker-in-android-application
 - Nesting CardView in RecyclerView for emotion cards based off the albums tutorial by
 	- Ravi Tamada, https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
 - Implementing the navigation drawer
 	- Android Developers tutorials and example app, https://developer.android.com/training/implementing-navigation/nav-drawer
 - Emotion counters in nav drawer
 	- Hari Vignesh Jayapalan, https://android.jlelse.eu/android-adding-badge-or-count-to-the-navigation-drawer-84c93af1f4d9
 - Storing/Loading data
 	- SharedPrefs tutorial
 		- Android Developer, https://developer.android.com/training/data-storage/shared-preferences
 	- ρяσѕρєя Kabunov, https://stackoverflow.com/a/11316855
 - Swipe to delete cards
 	- Aritra Roy, https://stackoverflow.com/a/42793834
 - Emotion icons https://materialdesignicons.com/icon/emoticon
    - Google
 	- Austin Andrews
