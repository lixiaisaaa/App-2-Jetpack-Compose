# App-2-Jetpack-Compose

Update Example35 to use Jetpack Compose!

Two main steps are needed:

1. Replace the xml + kotlin file for the main activity with the new jetpack compose type of programming.  (20 pts)

2. Replace the LiveData + observer pair in the main activity and the viewmodel with a StateFlow and a variable in the UI layer that grabs the state flow and updates the UI (as seen in class). You can leave the other flow connected to the database as is. Decide whether you need to leave the LiveData in the repository as is (in which case you need to figure out how to connect it to the viewmodel's StateFlow) or if you want to change it to a StateFlow also (10 pts).
