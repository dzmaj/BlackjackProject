# Blackjack
### Overview
This is a blackjack program that features betting, multiple players, and soft/hard aces. The focus was on Object Oriented Programming and making code that could be reused for other card games. I also wanted to simulate as much as possible the physical movement of real cards.

### Topics
- Java OOP
- Program design
- Collections
- Enum
- Sorting and using Comparator
- Debugging

### Lessons learned
- At the beginning of this project I tried to design all the classes in common.cards to be as universal as I could. I also intended from the beginning to be able to have stretch goals like multiple players. However, I didn't have things planned out as completely as I needed, and as I was coding the project I often got sidetracked with edge cases and working out logic that would be really unnecessary for the minimum project requirements. So I ended up spending more time than I wanted in order to get the project to minimum viability.
- Implementing soft aces was more difficult than I expected, but I ended up getting a lot of practice with the debugger and stepping thorough the program execution.
- I think maybe I could have used an interface for all the common functions of cards, groups of cards, and players. I ended up writing a lot of methods that I didn't end up using, or were long chains of methods.
- Figuring out unicode supplementary characters was interesting. I was stuck for a while trying to figure out how to put a utf16 character into a string. Eventually I ended up with String.valueOf(Character.toChars(int codePoint))

### How to Run
1. The user is prompted to enter their name
2. The user is prompted to enter information for any additional players. Players may be either human(controllable) or not.
3. The user will be prompted to enter a bet
4. Cards will be dealt to all the players in the game
5. Human players will have the option of either doubling down, hitting, or staying.
6. The dealer and any non-human players will take their turns
7. The winners and losers are determined
8. The total scores are displayed
9. The user has the option of continuing or exiting
