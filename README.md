# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.




NPC information in text file: 
MUST BE FORMATTED APPROPRIATELY IN THE FORM

int (total number of npcs)
String (description)
String (name)
int (x-position)
int (y-position)
int (wantsNum)
String, String, String ... (inventory as list of Strings)
String (want)

example: 
A man, perhaps in his mid thirties in richly dyed clothing who eyes you with curiosity 
merchant
1
1
1
dye, scissors, needles, bulky yarn, bobbin
gold


Location inventories in text file:
MUST BE FORMATTED APPROPRIATELY IN THE FORM

String, String, String, String

for each location, if a location does not have an inventory, 
leave the line blank, 
**YOU MUST HAVE A LINE FOR EACH LOCATION, BLANK OR FILLED IN**


 Design justification (including a brief discussion of at least one alternative you considered):
 
 My original design included a separate class for items, which yarn inherited from. As I made the game more complex, I realized that items would just be fancy strings, and a separate class wasn't necessary. In the future if I really want to make a ton of descriptions for random things, I would make items a separate class, but for now it just wasn't necessary. There was no additional functionality that a String didn't have already.

 Currently the Moth class does very little, and it could probably have been a boolean attribute in the Location class, but I would like to develop it further and give it additional methods in the future, such as having multiple lives (so the hero can swat it), or moving around to different locations randomly, so I chose to leave it as a separate class to make that easier in the future. 
 It's a similar situation with the talkingDoor class, right now it seems unreasonable to have him as a separate class when he could probably be just a specific NPC, or method in Play, but I'd like to make more riddles/obstacles in the future, so I made him a separate class. I also like the idea of the potion being able to free him from the door, so I want him to be a special type of NPC/separate class for future development.
 There is a stub class for Clothing because I wanted knit clothing items to have fun descriptions, but it didn't fit the timeline for this particular submission, so I left it to be completed later. It would involve needing to collect different items like "pattern" "dye" and then using them or having an NPC use them to do something special to the clothing.



 What was your **overall approach** to tackling this project?
 teeny, tiny, itty bitty steps at a time. Lots of automated tests.


 - What **new thing(s)** did you learn / figure out in completing this project?
 reading in files for java, creating custom exceptions, switch() in java instead of if/else, 

 - Is there anything that you wish you had **implemented differently**?
 

 - If you had **unlimited time**, what additional features would you implement?
 I really wish I had time to go make the moth a more imposing villain, able to flit around the map and be killed by a fly-swatter. 

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 Any of my test players who let me know what was clear to them, and what was really confusing as they played gave such great feedback about
 things like not knowing what they could pick up because the description was vague, they couldn't check their outfit so they didn't know what they had 
 already completed, etc. 
 Also, Prof. Crouser told me that making my map info read in from files would make it easier to update in the future and he was SO right, and I'm so glad I did that especially when I started adding mutable descriptions.

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 Not much, I really think I did a good job with the way I broke things down into little bits and waited to add complexity/fun features after the main basic functions were working.

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
