# Mars Rover
Author: Andrew Holman

### Table of Contents
**[Introduction](#introduction)**<br>
**[Build](#build)**<br>
**[Execution](#execution)**<br>
**[Program Design](#program-design)**<br>
**[Notes](#notes)**<br>

## Introduction
Mars Rover is a simple rover tracking program. An arbitrary number of robotic rovers are placed on a rectangular Martian
plateau and fed a variety of movement commands. When a rover has completed executing its command sequence, its new
new position is displayed. 

More information on the problem and program requirements can be found 
[here](https://code.google.com/archive/p/marsrovertechchallenge/).

## Build
The easiest way to build Mars Rover is with the included Gradle files. Windows users should use the Gradle Wrapper Batch
file while Unix users should use the Gradle Wrapper Shell script.

From the project root directory, build the program using the following commands: 

Windows: `.\gradlew.bat build`

Unix: `./gradlew build`

## Execution
Mars Rover provides two different methods for program execution depending on the existence of an optional command line
argument specifying an input file. Both execution methods display program output to standard out and standard error.

From the project root directory, execute the program using the following commands: 

Windows: `java -jar build\libs\marsrover-1.0.jar [filename]`

Unix: `java -jar build/libs/marsrover-1.0.jar [filename]`

####Input Constraints
* Plateau dimensions: Must be non-negative integer values
* Initial rover X coordinate: Must be integer value between 0 and plateau width
* Initial rover Y coordinate: Must be integer value between 0 and plateau height
* Initial rover heading: Must be one of {'N', 'E', 'S', 'W'} (Case-insensitive)
* Rover command sequence: Any combination of {'M', 'R', 'L'} (Case-insensitive) indicating move forward one space,
rotate 90&deg; clockwise and rotate 90&deg; counterclockwise respectively
    * Invalid command characters will be ignored

### Default: Interactive Command Line Mode
Running Mars Rover without providing additional command line arguments executes the application in interactive command 
line mode. Users are initially prompted to input the plateau's dimensions. From there, they are prompted to enter a 
rover's initial coordinates on the plateau and an initial heading, followed by a sequence of rover navigation commands. 
The rover's new position is displayed after executing the specified command sequence. This pattern of prompting for and 
receiving user input is continued until the user specifies the case-insensitive `quit` command.

Any invalid input will be rejected and the user will be prompted to re-enter the values.

* _A sample of this interaction can be found in the file_ `EXAMPLE_OUTPUT` _in the project root directory._

### With Optional Argument: File Input Mode
Running Mars Rover with an additional command line argument specifying an input file executes the application in file 
input mode. Plateau dimensions, initial rover positions, and rover command sequences are parsed from the specified file,
and final rover positions are displayed after executing the rover command sequences. 

If an error occurs while trying to open the specified file (e.g. it does not exist), the program will default to
interactive command line mode. 

If invalid or improperly formatted values are contained within the specified input file, the program will exit with an 
error message while attempting to parse the file. 

All input files should have the format: 

<pre>plateauUpperRightXValue plateauUpperRightYValue 
roverInitialXValue roverInitialYValue roverInitialHeading
roverCommandSequence
</pre>

## Program Design
Program execution begins with Main.java. A string containing the optional command line argument is passed to an 
ApplicationFactory, and either a FileApplication or UserApplication is returned based on the existence of the specified 
file. Main then executes the application's start method and the program begins regular execution. 

Since a broad overview of UserApplication and FileApplication execution is described in [Execution](#execution), I'll 
forgo their descriptions in this section and continue on to the underlying model used by both. 

When the program receives a line specifying plateau dimensions, the line is passed to the PlateauParser object. This
object parses and validates the values, then instantiates and returns a new Plateau object. Plateau objects implement
the Grid interface, which specifies functionality for determining if a certain space on the grid is valid.

When the program receives a line specifying an initial rover position, the line is passed to the MarsRoverParser object. 
This object parses the values, then instantiates and returns a new MarsRover object, provided the values were valid. 
Validation of the parsed values occurs in the MarsRover constructor; an exception is thrown if any values are invalid. 
Validation of initial values depends on the isValidSpace method of the Grid interface, as well as the Heading
enumeration.

Heading values are held in an enumeration, complete with methods allowing classes dependent on the enumeration to cycle
forward and backward through the values and an additional short-hand character token corresponding to each heading. If
an invalid character token is used to get a heading, an exception is thrown. 

The Rover interface, implemented by the MarsRover class, declares functionality for moving the rover forward, rotating
the rover 90&deg; in either direction, and obtaining the rover's position. Moving a MarsRover forward depends on the
Grid object declaring the space valid. Similarly, rotating the MarsRover 90&deg; clockwise or counterclockwise depends
on the Heading enumeration's getNext and getPrevious methods respectively. 

When the program receives a line containing a rover command sequence, the line is passed to the MarsRoverParser object
along with the rover to command. As the commands are parsed from the line, valid commands are executed on the rover,
while invalid commands are discarded. The Application object then displays the rover's new position. 

This process of defining new rovers, executing commands on them, and displaying their new positions continues until 
either the EOF is reached in the case of a FileApplication, or the `quit` command is received in the case of a 
UserApplication.

## Notes
Due to the simplicity of the program requirements, Plateau.java only needs to keep track of its width and height. 
Although this solution works well for the provided requirements, it is quite limited when considering real-world 
use cases. For example, should two rovers be allowed to occupy the same spot? 

A potential solution to this problem could be to define a two-dimensional array of boolean values, indicating whether or 
not the corresponding space in the plateau is occupied. If more information than this is needed, the array of booleans 
could be replaced by an array of rover objects.
