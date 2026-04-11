### GM Cuircuit Trainer

Created on 07/16/2025

- [x] Render list of workout plans            - 2025.11.27
- [x] Display one gif image with coidl     - 11.27
* \[x] Add simple counter with view model
* \[x] Load local gif - 11.28
* \[x] Push to Github - 12.3
* \[x] Add Play Button to each item on exercise list
* \[x] Make countdown timer re-run when workout ends | 11/19
* \[ ] Make the timer start by clicking start btn in workout list items | 11/30
* \[ ] Show Rest n Work when timer runs
* \[x] Change timer color when Work/Rest
* \[x] Load workout presets from viewmodel
* \[x] Show current exercise right
* \[x] Change Exercise name when circuit increase
* \[x] Select workout Preset in view model | 12/1
* \[x] Button Click effect for Preset card
* \[x] Show 'Finished' when workout ends | 12/2
* \[x] Circular indicator for timer
* \[x] Reverse circular timer
* \[x] Fix : Loading list of exercise images 12/14
* \[x] Make timer animation smooth.
* \[x] Export .apk
* \[x] Add room database - note after workout | 2026.01.03
* \[x] Simple Navigation Bar | 26.01.03
* \[x] Modal pop up to set initial circuit time | 26.01.09
* \[ ] Workout names crud in room
* \[ ] Export/import room backup
* \[ ] Hide extra bs when in workout mode
* \[x] Add todos.md  2026.03.29
* \[x] Update Gradle  26.03.29
* \[x] Show all exercises in list   4.1
* \[x] Add sample latest activity data n display in UI  4.3 
- [x] Show pop up modal after workout finishes to input note n save in progress activity list  4.7 done using launched effect 
- [] Highligt workout days in custom calendar
- [x] on click workout preset item show pop up of all exercies.   4/11/2026
- [] Add gif images for exercises


  Push to Google play store 

# Later
Now do only essentials. Later polish.
Modal pop up after workout finish to save a note about feeling 
Save workout date n time n feeling in a note after workout finished
Export/import room backup
Show the days you did workout in Calendar - track progress like Github table
Play sound after each round done
Show total time countdown in minutes n seconds
Select from pre-made workout plans n download them for practice offline - paid training routines
Set default circut time 25.30/35 in settings page

Simple Gym workout timer. No DB for now. Just show Exercises n timer. 7/16/2025
Convert Denis Panjuta Workout App to Jetpack. And make it use database to save workouts n feelings. Adidas Running simple Clone
Build your own custom workout plans n suggest them to friends by link 

Data structure 

Finished workouts - Recent activities - Training plans - Weekly streak
{ id - title - exercises,
total Time : mm:ss (duration)
calories burned 
date - time 
Note : feeling after workout 
rate : 1-5
Environment : indoor/outdoor
Location : Geocoding
City : London, Paris, ... 
Weather [hot, cold, sunny, rainy, partly cloudy, mild, perfect. ]
presetId }
