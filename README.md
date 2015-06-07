
#Intelligent Agents Assignment
This assignment was to demonstrate some techniques used within Intelligent Agents on a simple example. My main focus was using the belief-desire-intention model in this task. The solution is certainly not perfect, as
in this solution an agents beliefes are effectively erased in each moment and then regathered. They should be persistent between moments, and then modified to reflect new perceptions of the world. In terms of desires, there is only
one - to eat lots of plants. Although it would be beneficial for the creature to have desires to eat a particular type of plant so it can prioritise whether it needs energy or needs health. Intentions are actions the creature intends to
do - and in most cases will do. Section 2 & 3 have examples of these, although Section 3 does not perform as well as 2 because it evaluates its options too frequently leading it to change its intention frequently. This is caused by the poor vision each creature has so in each moment their perception is potentially changing quite a lot. 


##The Environment
The environment within sections 1,2,3 remains the same throughout, it is only the creatures within the environment that change. 

There are two types of plants in the world:
* Medicinal plants (pink) – these help restore a creatures health
* Energy plants (green) – these given the creatures energy

A plant spawns with a value between 10 and 20, this value is how much energy or health is restored when a creature eats that plant. In each moment a plant grows, therefore this value grows slightly. It can grow between 0-2 in each moment, which is decided at random. 

The world is represented as a 20x20 enclosed grid based world. 
A creature may only do one action in each moment, with example actions including eating and moving. A creature may only move one square at a time and can move in the following directions; up, down, left and right (4 way movement). 

All types of creatures begin with a health of 100 with it being decreased by 5 in every moment. So a creature can survive entirely on energy plants for 20 moments. However to increase its lifespan it must eat medicinal plants. The maximum health a creature can have is 100, if it eats plants while having full health then it’s just destroying a plant for no reason - so it's in a creatures interests to 'save it for later'. When a creature’s health reaches 0, the creature dies.

All types of creatures begin with a value of 50 representing the energy it has, in each moment a creature loses 2 from its energy. Moving costs a creature 5 more additional energy. The maximum energy for a creature is 100. If a creature’s energy reaches 0, it becomes paralyzed and will eventually die.
Plants and creatures in the environment are spawned in random positions each time the program is run, with 20 of each plant and 5 creatures.
Lastly all creatures have a size attribute, creatures spawn with a random size at first but can grow this by eating plants.

If two creatures try and move into the same square in the same moment, the creature with the largest size 'wins' the square. The other creature loses its move. 

##Section 1
In section 1 creatures just move about randomly, trivial example to compare later sections with.

##Section 2
In section 2 creatures move to the plant that *looks* to be closest. The creatures do not have perfect vision so it is possible they go to a plant that is not the closest one. Once a creature spots a plant it will travel to the location it saw it - it will not reconsider its intentions and will find out whether the plant exists or not when it reaches the spot it was seen at. 

##Section 3
These creatures will reconsider their intentions in each moment and change their intention based on the information they have in each moment. Due to the poor vision on the creatures this is not a good tactic as this creature tends to spend more time changing its intentions than actually carrying any of them out. 

##Future Improvements
* Add a new creature that has perfect (or near perfect) vision. 
* Fix bug with conflicting intentions, creatures can move in the same square on occasion. 
* Agent Communication Language between creatures - with each type being able to communicate with another creature of the same type. Adds a variety of possibilities like coordination, exchange of knowledge, etc.
* Improve visual display - very simple at the moment to demonstrate what is happening rather than looking all shiny. 

