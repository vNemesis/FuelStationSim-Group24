@echo off
cls
:start

set /P numOfPumps="Number of pumps: "
set /P numOfTills="Number of tills: "
set /P ProbP="Probability of P: "
set /P ProbQ="Probability of Q: "
set /P enableTrucks="Enable Trucks? true/false: "
set /P seed="Simulation seed: "
set /P ticks="Ticks to run sim: "
set /P ptf="Print to Text file? true/false: "
set /P ptfcsv="Print to CSV file? true/false: "
set /P debug="Show debug information? true/false: "

java -jar Fuel_Station_Simulation_(Executable)-Group24.jar %numOfPumps% %numOfTills% %ProbP% %ProbQ% %enableTrucks% %seed% %ticks% %ptf% %debug% %ptfcsv%

set choice=
echo ""
set /p choice="Do you want to restart? [y/n]: "
if not '%choice%'=='' set choice=%choice:~0,1%
if '%choice%'=='y' goto start
if '%choice%'=='n' exit

pause