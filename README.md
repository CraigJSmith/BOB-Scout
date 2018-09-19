# BOB-Scout
BOB Scout is an Android app allowing for the streamlined, user-friendly collection and storage of scouting data (data about other teams' robot performance). It was developed for FRC team 85 Built on Brains (BOB) for the FIRST Robotics Competition 2018 season. Once collected, data is stored in a series of CSV files for analysis by other software. The goal of the data collected in this app is to the calculate average amount of time taken for a robot to complete particular tasks as well as their capabilities and reliability. The analysis of this data, however, is left to other software.

This app is intended for use on Android tablets with screens 6.5" and greater. Smaller screen sizes may result in unpredictable UI behavior.

## Usage

![Setup Screenshot](https://raw.githubusercontent.com/craigjsmith/BOB-Scout/master/screenshots/Setup.png)
### Setting up
Every match, the user is assigned a team to scout (collect data on). Assignment is dependent on the user's ID (specified in Settings) and the match schedule (stored as a CSV) SEE X. Alternatively, match and team number may be manually specified by the user.

![Setup Screenshot](https://raw.githubusercontent.com/craigjsmith/BOB-Scout/master/screenshots/Autonomous.png)
### Autonomous Period
Once scouting has begun, the user is presented the Autonomous scouting screen. The data collected on this screen corresponds to the first 15 seconds of the match where the robots are autonomously controlled; no driver control. The scouter is asked to specify the starting position of the robot and whether or not the robot crossed the autoline, put a cube on the switch, put a cube on the scale, or picked up another cube. At the conclusion of the Autonomous period, the user will press "Start Teleop"

![Setup Screenshot](https://raw.githubusercontent.com/craigjsmith/BOB-Scout/master/screenshots/Teleoperated.png)
### Teleoperated Period
The user is presented with a top down map of the game field. This should correlate with the scouter's view of the field (by the default the Red alliance's side appears on the left, this may be changed in Settings). The scouter will press the button correlatng to the game element the robot interacts with once when the robot approaches it. The button will light up lime green indicating this is now the robot's current location. The timer at the top of the screen indicates the time that the robot has spent at that location thus far. The user will press once more when the robot leaves. This is continued throughout the match, after which the user will press "Stop" at the top of the screen.

![Setup Screenshot](https://raw.githubusercontent.com/craigjsmith/BOB-Scout/master/screenshots/End.png)
### Match End
At the end of the match, the scouter is prompted to input data relating to that match. If the scouter believes their data is problematic they can opt to mark their scouting data as such. After the relevant information is filled out, the user will press "Submit".

![Setup Screenshot](https://raw.githubusercontent.com/craigjsmith/BOB-Scout/master/screenshots/Submit.png)
### Submission
After submission, the user is presented with the raw text of the data they collected for that match. This is saved on the device in /BOBScout/Matches/ as [MATCH NUM]:[TEAM NUM].csv. This data can then be consollidated from all scouters and analyzed.
