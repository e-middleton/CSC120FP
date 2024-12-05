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
*yarn types should only have the first word, ommitt "yarn"

int (total number of npcs)
String (description)
String (name)
int (x-position)
int (y-position)
int (wantsNum)
String String String ... (inventory as list of Strings)
String (want)

example: 
A man, perhaps in his mid thirties in richly dyed clothing who eyes you with curiosity 
merchant
1
1
1
dye scissors needles bulky bobbin
gold


Location inventories in text file:
MUST BE FORMATTED APPROPRIATELY IN THE FORM

String String String String

for each location, if a location does not have an inventory, 
leave the line blank, 
**YOU MUST HAVE A LINE FOR EACH LOCATION, BLANK OR FILLED IN**

