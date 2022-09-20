
# DB Interview Spark Scala

It is a Maven project based on `Spark 2.4.7` and Scala `2.11.8`

### Dataset Model

>Dataset is composed 2 JSON files that act as a dictionary containing each player basic information.
Apart from that, there are 5 different CSV files that contain different stats related to the players on different areas of the game.
> 
> Although it is slightly modified. It is based on [UCL UEFA Champions League 21/22 Dataset](https://www.kaggle.com/datasets/azminetoushikwasi/ucl-202122-uefa-champions-league)

#### players1.json
- `club`: String 
- `player_name`: String 
- `position`: String 

#### players2.json
- `club`: String 
- `player_name`: String 
- `position`: String 

#### attacking.csv
- `player_name`: String 
- `club`: String 
- `position`: String 
- `assists`: Integer 
- `corner_taken`: Integer 
- `offsides`: Integer 
- `dribbles`: Integer 

#### distr.csv
- `player_name`: String 
- `club`: String 
- `position`: String 
- `pass_accuracy`: Double 
- `pass_attempted`: Integer 
- `pass_completed`: Integer 
- `cross_accuracy`: Integer 
- `cross_attempted`: Integer 
- `cross_complted`: Integer 
- `freekicks_taken`: Integer 

#### goalkeeping.csv
- `player_name`: String 
- `club`: String 
- `position`: String 
- `saved`: Integer 
- `conceded`: Integer 
- `saved_penalties`: Integer 
- `cleansheets`: Integer 
- `punches_made`: Integer 

#### goals.csv
- `player_name`: String 
- `club`: String 
- `position`: String 
- `goals`: Integer 
- `right_foot`: Integer 
- `left_foot`: Integer 
- `headers`: Integer 
- `others`: Integer 
- `inside_area`: Integer 
- `outside_areas`: Integer 
- `penalties`: Integer 

#### key_stats.csv
- `player_name`: String 
- `club`: String 
- `position`: String 
- `minutes_played`: Integer 
- `match_played`: Integer 
- `goals`: Integer 
- `assists`: Integer 
- `distance_covered`: String 
