@echo off

set /P numOfPumps="Number of pumps: "
set /P numOfTills="Number of tills: "
set /P ProbP="Probability of P: "
set /P ProbQ="Probability of Q: "
set /P enableTrucks="Enable Trucks true/false: "
set /P seed="Simulation seed: "
set /P ticks="Ticks to run sim: "
set /P ptf="Print to file true/false: "

java -jar Fuel Station Simulation - Group 24.jar %numOfPumps% %numOfTills% %ProbP% %ProbQ% %enableTrucks% %seed% %ptf%

pause