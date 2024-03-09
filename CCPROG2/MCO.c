#include <stdio.h>
#include <string.h>

typedef char String250[251]; //for summary
typedef char String30[31]; //for actor names
typedef char String20[21]; //for movie name
typedef char String10[11];

struct hallSeating{
    int LowerBox[3][10]; //regular: 200 | premiere: 300
    int UpperBox[2][10]; //regular: 400 | premiere: 500
};

struct movieInfo{
    String20 name;
    int length; //max of 180 mins
    String10 rating; //G, PG-13, PG-15, R-18
    String250 summary;
    String30 actors[3];
};

struct date{
    int month;
    int day;
    int year;
}enter;

struct movieTime{
    struct movieInfo time1;
    struct movieInfo time2;
    struct movieInfo time3;
    struct hallSeating time[3];
    char status;
    int hallNumber;
    char seating;
};

struct dateSched{
    struct movieTime halls[4];
    struct date period;
    int movieday;
    int seatSold[3];
}date[365];

struct fileMain{
    struct movieInfo movieOne;
    struct movieInfo movieTwo;
    struct movieInfo movieThree;
    struct movieInfo movieFour;
    struct movieInfo movieFive;
    int daysplayed[5];
}c;

/* input is always called when an input is needed. 
It is responsible for most of the inputs like deciding which function would be used in the main func.
@param inputVal - The value in this parameter determines the what input is needed (1 - 2 choices, 2 - 3 choices, 3 - 4 choices, 4 - 5 choices).
@param begin - It is what will be printed before takin in the input.
@var ret is the input of the user.
@var done is for the do-while loop so that when the input is correct it will stop the loop.
@return ret when all conditions are met.
Pre-condition: inputVal should only be from 1-4.
*/
int input(int inputVal, String20 begin) //1 - 2 choice, 2 - 3 choices, 3 - 4 choices, 4 - 5 choices
{
    int ret = 0, done = 0;

    do
    {
        printf("%s", begin);
        scanf("%d", &ret);
        
        switch(ret)
        {
            case 1: case 2: done = 1; 
                    
                    break;
            case 3: if(inputVal == 2 || inputVal == 3 || inputVal == 4) //only applicable if inputVal is 2 or 3 or 4
                    {    
                        done = 1; 
                        break;
                    }
            case 4: if(inputVal == 3 || inputVal == 4) //only applicable if inputVal is 4 or 3
                    {
                        done = 1;
                        break;
                    }
            case 5: if(inputVal == 4) //only applicable if inputVal is 4
                    {    
                        done = 1; 
                        break;
                    }
            default: printf("ERROR: The input is incorrect.\n"); break;
        }
    }while(done != 1);

    return ret; 
}

/* numtochar takes in a value from 1-4 then returns the corresponding to letter depending on the if-else condition.
@param start - The value in this parameter determines what letter would be returned.
@param val - This is for the if-else condition so that if it is 1 then it will return A or O or R or V else A or R.
@var ret is the character that would be returned.
@return ret when function ret gets the letter.
Pre-condition: start is only from 1-4
*/
char numtochar(int start, int val)
{
    char ret;
    
    if(val == 1) //For the status of a cinema hall
    {
        switch(start)
        {
            case 1: ret = 'A'; break;
            case 2: ret = 'O'; break;
            case 3: ret = 'R'; break;
            case 4: ret = 'V'; break;
        }
    }
    else //For the seating arrangement of a cinema hall
    {
        if(start != 1)
            ret = 'A';
        else    
            ret = 'R';
    }

    return ret;
}

/* dateInput takes in two inputs. 1st is for the month, and 2nd is for the day. The year for the date would be 2023.
@var i is what will be returned once the function is done. It is the index for the struct date[i].
@var j is needed for the for loop iterating up to 12. Depending on what the value j, the for-loop would add 28-31 to i.
@var x is used in the do-while loop. If all the inputs are correct, then x will be 1 stopping the do-while loop.
@return i when all the inputs are correct.
*/
int dateInput()
{
    int i = 0, j, x = 0;
    do
    {
        printf("Enter month: ");
        scanf("%d", &enter.month); //month input
        if(enter.month < 1 || enter.month > 12)
            printf("ERROR: The input is incorrect.\n");
        else
        {
            printf("Enter day: ");
            scanf("%d", &enter.day); //day input
            
            if(enter.month == 2 && !(enter.day >= 1 && enter.day <= 28))
                printf("ERROR: The input is incorrect.\n");
            else if((enter.month == 4 || enter.month == 6 || enter.month == 9 || enter.month == 11) && !(enter.day >= 1 && enter.day <= 30))
                printf("ERROR: The input is incorrect.\n");
            else if((enter.month == 1 || enter.month == 3 || enter.month == 5 || enter.month == 7 || enter.month == 8 || enter.month == 10 || enter.month == 12) && !(enter.day >= 1 && enter.day <= 31))
                printf("ERROR: The input is incorrect.\n");
            else
            {
                printf("Year: 2023\n\n");
                x = 1;
            }
        }
    }while(x != 1);
    
    i = enter.day;

    if(enter.month != 1)
    {
        for(j = 2; j <= enter.month; j++)
        {
            if(j == 2)
                i += 28;
            else if(j == 4 || j == 6 || j == 9 || j == 11)
                i += 30;
            else if(j != 13)
                i += 31;
        }
    }

    return i; //index of struct date
}

/* hallIdentifier is responsible for finding the index of where a hall number is located in date[].halls[].hallsNumber.
@param p1[] gets the address of struct date[].
@param num is the hall number that would be needed to find.
@param index gets the index for p1[].
@var i is used in the for loop to find the index only if p1[index].halls[i].hallNumber is equal to num.
@var j will be returned if ctr is equal to 1. j will get the var i if condition is met.
@var ctr is added up to a certain number within the for-loop. If condition is met then if ctr is only 1 the function returns j.
@return j if ctr is 1 else @return -1 indicating that there are more than 1 of p1[index].halls[i].hallNumber that has the num or there isn't one of them that contains it.
*/
int hallIdentifier(struct dateSched p1[], int num, int index)
{
    int i, j = 0, ctr = 0;

    for(i = 0; i < 4; i++)
    {
        if(p1[index].halls[i].hallNumber == num)
        {
            ctr++;
            j = i;
        }
    }
    if(ctr == 1)
        return j; //index of p1[index].halls[i].hallNumber that contains num

    return -1;
}

/*fileMaintenance controls the data of halls and movies. For the halls, it is able to change what number of the hall is and the status of a hall.
For the movies: the movie name, the length of the movie, the rating of a movie, its summary, and the name of the three leading actors.
@param *a is a pointer that has the address of struct c.
@param b[] contains the address of date[].
@var i is used within the for loop that would print the three leading actors' names. It also acts as the index of movie->actors[i].
@var choice holds the value of what would be edited between cinema halls or movies. input is called to give the value to choice.
@var index is used as the index for b[j].halls[index].
@var movieNumber is used to hold the value of which movie is going to be used (movieOne - movieFive).
@var action2 is used in fileMaintenance many times. Whenever action1 has the value of either 3 or 4, action2 will have the value of what information is going to be updated or deleted.
@var action1 determines what action is going to be executed: (1)Create, (2)Read, (3)Update, and (4)Delete.
@var rating holds the value of what string is going to be in (*movie).rating.
@var j is the index of b[].
@var hallChoice holds which cinema hall is going to be used.
@var t is part of the condition do-while loop that determines if the chosen hall had already been used or not. When t = 0 the loop stops.
@var l is also part of a do-while loop that determines if the chosen hall had already been used or not.
@struct *movie acts as the placeholder of the address of one of a->movieOne to a->movieFive depending on the value movieNumber has.
*/
void fileMaintenance(struct fileMain *a, struct dateSched b[])
{
    int i, choice = 0, index = -1, movieNumber = 0, action2 = 0, action1 = 0, rating = 0, j, hallChoice = 0, t = -1, l;
    struct movieInfo *movie;

    printf("\nPick between what to edit\n");

    choice = input(1, "Cinema Halls or Movies: ");
    
    if(choice == 1)
    {   
        j = dateInput() - 1; //index for b
        do
        {
            printf("Enter index (0-3): ");
            scanf("%d", &index);
            if(!(index >= 0 && index <= 3))
                printf("ERROR: The input is incorrect.\n");
        }while(index != 0 && index != 2 && index != 3 && index != 1);
        
        action1 = input(3, "(1)Create, (2)Read, (3)Update, and (4)Delete: ");

        switch(action1)
        {
            case 1: do //determines if the value hallChoice had already been used
                    {
                        l = 0;
                        hallChoice = input(3, "Enter hall number (1-4): ");
                        
                        if(b[j].halls[index].hallNumber != hallChoice)
                        {
                            if(b[j].halls[0].hallNumber == hallChoice && index != 0)
                                l += 1;
                            if(b[j].halls[1].hallNumber == hallChoice && index != 1)
                                l += 1;
                            if(b[j].halls[2].hallNumber == hallChoice && index != 2)
                                l += 1;
                            if(b[j].halls[3].hallNumber == hallChoice && index != 3)
                                l += 1;
                        }

                        if(l < 1)
                            t = 0;
                        else
                            printf("\nHall number already exists\n");
                    }while(t != 0);

                    b[j].halls[index].hallNumber = hallChoice; //gives the hall number to index that was entered
                    b[j].halls[index].status = numtochar(input(3, "Enter hall status (1)ACTIVE, (2)OCCUPIED, (3)RESERVED, (4)VACANT: "), 1);

                    break;
            case 2: printf("\nHall number: %d", b[j].halls[index].hallNumber); //shows the hall number and status
                    printf("\nHall status: %c", b[j].halls[index].status); 

                    break;
            case 3: printf("\n(1) HALL NUMBER\n(2) HALL STATUS\n"); //chooses which one will be changed between the hall number and hall status
                    action2 = input(1, "Pick the information you want to change: ");
                    if(action2 == 1)
                    {
                        do
                        {
                            l = 0;
                            hallChoice = input(3, "Enter hall number (1-4): ");
                            
                            if(b[j].halls[index].hallNumber != hallChoice)
                            {
                                if(b[j].halls[0].hallNumber == hallChoice && index != 0)
                                    l += 1;
                                if(b[j].halls[1].hallNumber == hallChoice && index != 1)
                                    l += 1;
                                if(b[j].halls[2].hallNumber == hallChoice && index != 2)
                                    l += 1;
                                if(b[j].halls[3].hallNumber == hallChoice && index != 3)
                                    l += 1;
                            }

                            if(l < 1)
                                t = 0;
                            else
                                printf("\nHall number already exists\n");
                        }while(t != 0);

                        b[j].halls[index].hallNumber = hallChoice;
                    }
                    else
                        b[j].halls[index].status = numtochar(input(3, "Enter hall status (1)ACTIVE, (2)OCCUPIED, (3)RESERVED, (4)VACANT:  "), 1);

                    break;
            case 4: printf("\n(1) HALL NUMBER\n(2) HALL STATUS\n"); //chooses which one will be deleted
                    action2 = input(1, "Pick the information you want to delete: ");
                    if(action2 == 1)
                        b[j].halls[index].hallNumber = 0;
                    else
                        b[j].halls[index].status = 'V';

                    break;
        }
    }
    else
    {
        printf("\n");
        movieNumber = input(4, "Enter movie number (1-5): "); //picks what member is going give the address to the struct movie
        switch(movieNumber)
        {
            case 1: movie = &a->movieOne; break;
            case 2: movie = &a->movieTwo; break;
            case 3: movie = &a->movieThree; break;
            case 4: movie = &a->movieFour; break;
            case 5: movie = &a->movieFive; break;
        }
        
        action1 = input(3, "(1)Create, (2)Read, (3)Update, and (4)Delete: ");

        switch(action1)
        {
            case 1: printf("Enter movie name (20 CHARACTERS ONLY): "); //Enters the movie name, rating, summary, and lead actors
                    scanf("%20s", movie->name);

                    do
                    {
                        printf("Enter movie length (1-180): ");
                        scanf("%d", &movie->length);

                        if((*movie).length < 1 || (*movie).length > 180)
                            printf("ERROR: The input is incorrect.\n");
                    }while((*movie).length < 1 || (*movie).length > 180);

                    rating = input(3, "Enter movie rating (1)\"G\", (2)\"PG-13\", (3)\"PG-15\"), (4)\"R-18\": ");
                    if(rating == 1)
                        strcpy((*movie).rating, "G");
                    else if(rating == 2)
                        strcpy((*movie).rating, "PG-13");
                    else if(rating == 3)
                        strcpy((*movie).rating, "PG-15");
                    else
                        strcpy((*movie).rating, "R-18");
                        
                    printf("Enter movie summary (250 CHARACTERS ONLY): ");
                    scanf("%250s", movie->summary);
                    
                    printf("Enter lead actors (1-3) (30 CHARACTERS PER NAME): ");
                    scanf("%30s%30s%30s", (*movie).actors[0], (*movie).actors[1], (*movie).actors[2]);

                    break;
            case 2: printf("\nMovie name: %s", movie->name); //prints the information of the movie
                    printf("\nMovie length: %d", movie->length);
                    printf("\nMovie rating: %s", movie->rating);
                    printf("\nSummary: %s", movie->summary);
                    printf("\nLead actors: ");
                    for(i = 0; i < 3 && (*movie).actors[i] != 0; i++)
                        printf("\n%d. %30s", i + 1, movie->actors[i]);

                    break;
            case 3: printf("\n(1) MOVIE NAME\n(2) MOVIE LENGTH\n(3) MOVIE RATING\n(4) SUMMARY\n(5) LEAD ACTORS\n"); //picks which information will be changed
                    action2 = input(4, "Pick the information you want to change: ");
                    if(action2 == 1)
                    {
                        printf("Enter movie name (20 CHARACTERS ONLY): ");
                        scanf("%20s", movie->name);
                    }
                    else if(action2 == 2)
                    {
                        do
                        {
                            printf("Enter movie length (1-180): ");
                            scanf("%d", &movie->length);

                            if((*movie).length < 1 || (*movie).length > 180)
                                printf("ERROR: The input is incorrect.\n");
                        }while((*movie).length < 1 || (*movie).length > 180);
                    }
                    else if(action2 == 3)
                    {
                        rating = input(3, "Enter movie rating (1)\"G\", (2)\"PG-13\", (3)\"PG-15\"), (4)\"R-18\": ");
                        if(rating == 1)
                            strcpy((*movie).rating, "G");
                        else if(rating == 2)
                            strcpy((*movie).rating, "PG-13");
                        else if(rating == 3)
                            strcpy((*movie).rating, "PG-15");
                        else
                            strcpy((*movie).rating, "R-18");
                    }
                    else if(action2 == 4)
                    {
                        printf("Enter movie summary (250 CHARACTERS ONLY): ");
                        scanf("%250s", movie->summary);
                    }
                    else
                    {
                        printf("Enter lead actors (1-3) (30 CHARACTERS PER NAME): ");
                        scanf("%30s %30s %30s", (*movie).actors[0], (*movie).actors[1], (*movie).actors[2]);
                    }

                    break;
            case 4: printf("\n(1) MOVIE NAME\n(2) MOVIE LENGTH\n(3) MOVIE RATING\n(4) SUMMARY\n(5) LEAD ACTORS\n"); //picks the movie information to be deleted
                    action2 = input(4, "Pick the information you want to delete: ");
                    if(action2 == 1)
                        strcpy((*movie).name, "");
                    else if(action2 == 2)
                        (*movie).length = 0;
                    else if(action2 == 3)
                        strcpy((*movie).rating, "");
                    else if(action2 == 4)
                        strcpy((*movie).summary, "");
                    else
                    {
                        strcpy((*movie).actors[0], "");
                        strcpy((*movie).actors[1], "");
                        strcpy((*movie).actors[2], "");
                    }
                    break;
        }
    }
}

/* compareStruct is a function in which movie names are compared to emp.name if they contain any letters or not.
@param x determines which condition should be used based on what value it contains.
@param a is the index for date[a].
@param b is the index for date[a].halls[b].
@struct emp is needed to compare emp.name with struct members that are movie names.
@var ret is the character that would be returned.
@return 1 if a condition is met or 0 if not.
Pre-condition: x should only be from 1 to 8.
*/
int compareStruct(int x, int a, int b)
{
    struct movieInfo emp;

    strcpy(emp.name, ""); //sets emp.name to ""

    if(x == 1 && strcmp(c.movieOne.name, emp.name) != 0) //if c.movieOne.name is equal to emp.name returns 1
        return 1;
    else if(x == 2 && strcmp(c.movieTwo.name, emp.name) != 0) 
        return 1;
    else if(x == 3 && strcmp(c.movieThree.name, emp.name) != 0) 
        return 1;
    else if(x == 4 && strcmp(c.movieFour.name, emp.name) != 0) 
        return 1;
    else if(x == 5 && strcmp(c.movieFive.name, emp.name) != 0) 
        return 1;
    else if(x == 6 && strcmp(date[a].halls[b].time1.name, emp.name) != 0) //if date[a].halls[b].time1.name is equal to emp.name returns 1
        return 1;
    else if(x == 7 && strcmp(date[a].halls[b].time2.name, emp.name) != 0) 
        return 1;
    else if(x == 8 && strcmp(date[a].halls[b].time3.name, emp.name) != 0) 
        return 1;

    return 0;
}

/*schedule controls what the schedule is going to be depending on the date, which movie hall, and time slot. The seating arrangement is also edited within the function.
@param *ptr2 is a pointer that has the address of struct c.
@param ptr1[] contains the address of date[].
@param index is used for the index ptr1[].
@var time holds the value of what time is going to be used.
@var satisfied is part of a do-while loop that is when equals to 2 then it is going to ends the most of the function.
@var selectedMovie is used to have the value of which movie will be shown for a time slot. It is also in three switch statements.
@var choice is in a switch statement that determines which string will be used to in the printing of "Time Schedule for Hall %s"
@var d is part of a do-while loop and switch statement. If the conditions are met the do-while loop stops.
@var z is in a condition if z is equal to 1 the days of a movie played will increase.
@var k is inside a for loop that serves as the row of UpperBox[k][] and LowerBox[k][]
@var l is inside a for loop that serves as the column of UpperBox[][l] and LowerBox[][l]
@var g holds the value of the hallIdentifier when called. It's used in a condition that indicates if a hall doesn't exist.
*/
int schedule(struct dateSched ptr1[], struct fileMain *ptr2, int index)
{
    int time, selectedMovie, satisfied, choice = 0, d = 0, z, k, l, g = 0;
    String20 str;

    choice = input(3, "Enter movie hall to edit schedule: ");
    g = hallIdentifier(ptr1, choice, index); //determines if hall doesn't exist
    if(g != -1)
    {
        switch (choice)
        {
            case 1: strcpy(str, "One"); break;
            case 2: strcpy(str, "Two"); break;
            case 3: strcpy(str, "Three"); break;
            case 4: strcpy(str, "Four"); break;
        }

        do
        {
            printf("\nTime Schedule For Hall %s | %d/%d/%d", str, ptr1[index].period.month, ptr1[index].period.day, ptr1[index].period.year); //prints the time sched for the chosen hall
            printf("\n10:00 AM - %s", ptr1[index].halls[g].time1.name);
            printf("\n01:00 PM - %s", ptr1[index].halls[g].time2.name);
            printf("\n04:00 PM - %s", ptr1[index].halls[g].time3.name);
            printf("\nSeating arrangment - %c", ptr1[index].halls[g].seating);

            if(ptr1[index].halls[g].status != 'O') //stops the function if the status is ON-HOLD
            {
                printf("\nHall status - %c\n", ptr1[index].halls[g].status);

                if(ptr1[index].halls[g].status == 'A' || ptr1[index].halls[g].status == 'V' || (ptr1[index].halls[g].status == 'R' && ptr1[g].movieday == 0)) //the last part of the condition means that the status is RESERVED and that there is only one movie or no movie played at all
                {
                    printf("\nDo you want to edit the time sched and seating arrangement?\n");
                
                    satisfied = input(1, "(1)Yes or (2)No: ");
                    if(satisfied == 1)
                    {
                        time = input(2, "Enter time to add or change the movie (1)10 AM, (2)1 PM, (3)4 PM: ");

                        do
                        {
                            selectedMovie = input(4, "Enter the movie for the selected time period (1-5): ");

                            switch(selectedMovie)
                            {
                                case 1: if(ptr2->daysplayed[0] < 5 && compareStruct(selectedMovie, 0, 0) == 1) //this means that the movie hadn't been played for 5 days and that the movie name does have a name
                                            d = 1;
                                        else
                                        {
                                            printf("ERROR: The input is incorrect.\n");
                                            if(compareStruct(selectedMovie, 0, 0) != 1) //if the movie doesn't have a name the function will end
                                                return 0;
                                        }
                                        
                                        break;
                                case 2: if(ptr2->daysplayed[1] < 5 && compareStruct(selectedMovie, 0, 0) == 1)
                                            d = 1;
                                        else
                                        {
                                            printf("ERROR: The input is incorrect.\n");
                                            if(compareStruct(selectedMovie, 0, 0) != 1)
                                                return 0;
                                        }
                                        
                                        break;
                                case 3: if(ptr2->daysplayed[2] < 5 && compareStruct(selectedMovie, 0, 0) == 1)
                                            d = 1;
                                        else
                                        {
                                            printf("ERROR: The input is incorrect.\n");
                                            if(compareStruct(selectedMovie, 0, 0) != 1)
                                                return 0;
                                        }
                                        
                                        break;
                                case 4: if(ptr2->daysplayed[3] < 5 && compareStruct(selectedMovie, 0, 0) == 1)
                                            d = 1;
                                        else
                                        {
                                            printf("ERROR: The input is incorrect.\n");
                                            if(compareStruct(selectedMovie, 0, 0) != 1)
                                                return 0;
                                        }
                                        
                                        break;
                                case 5: if(ptr2->daysplayed[4] < 5 && compareStruct(selectedMovie, 0, 0) == 1)
                                            d = 1;
                                        else
                                        {
                                            printf("ERROR: The input is incorrect.\n");
                                            if(compareStruct(selectedMovie, 0, 0) != 1)
                                                return 0;
                                        }
                                        
                                        break;
                            }
                        }while(d != 1);

                        if(ptr1[index].halls[g].status != 'R')
                            ptr1[index].halls[g].status = 'A'; //the status will become active of the hall if it isn't reserved

                        switch(time)
                        {
                            case 1: if(selectedMovie == 1)
                                        strcpy(ptr1[index].halls[g].time1.name, (*ptr2).movieOne.name); //The first movie will be scheduled for the first time slot
                                    else if(selectedMovie == 2)
                                        strcpy(ptr1[index].halls[g].time1.name, (*ptr2).movieTwo.name);
                                    else if(selectedMovie == 3)
                                        strcpy(ptr1[index].halls[g].time1.name, (*ptr2).movieThree.name);
                                    else if(selectedMovie == 4)
                                        strcpy(ptr1[index].halls[g].time1.name, (*ptr2).movieFour.name);
                                    else
                                        strcpy(ptr1[index].halls[g].time1.name, (*ptr2).movieFive.name); //The last movie will be scheduled for the first time slot
                                    
                                    if(selectedMovie == 1 || selectedMovie == 2 || selectedMovie == 3 || selectedMovie == 4)
                                        z = 1;

                                    break;
                            case 2: if(selectedMovie == 1)
                                        strcpy(ptr1[index].halls[g].time2.name, (*ptr2).movieOne.name); //The first movie will be scheduled for the second time slot
                                    else if(selectedMovie == 2)
                                        strcpy(ptr1[index].halls[g].time2.name, (*ptr2).movieTwo.name);
                                    else if(selectedMovie == 3)
                                        strcpy(ptr1[index].halls[g].time2.name, (*ptr2).movieThree.name);
                                    else if(selectedMovie == 4)
                                        strcpy(ptr1[index].halls[g].time2.name, (*ptr2).movieFour.name);
                                    else
                                        strcpy(ptr1[index].halls[g].time2.name, (*ptr2).movieFive.name); //The last movie will be scheduled for the second time slot
                                    
                                    if(selectedMovie == 1 || selectedMovie == 2 || selectedMovie == 3 || selectedMovie == 4)
                                        z = 1;

                                    break;
                            case 3: if(selectedMovie == 1)
                                        strcpy(ptr1[index].halls[g].time3.name, (*ptr2).movieOne.name); //The first movie will be scheduled for the last time slot
                                    else if(selectedMovie == 2)
                                        strcpy(ptr1[index].halls[g].time3.name, (*ptr2).movieTwo.name);
                                    else if(selectedMovie == 3)
                                        strcpy(ptr1[index].halls[g].time3.name, (*ptr2).movieThree.name);
                                    else if(selectedMovie == 4)
                                        strcpy(ptr1[index].halls[g].time3.name, (*ptr2).movieFour.name);
                                    else
                                        strcpy(ptr1[index].halls[g].time3.name, (*ptr2).movieFive.name); //The last movie will be scheduled for the last time slot
                                    
                                    if(selectedMovie == 1 || selectedMovie == 2 || selectedMovie == 3 || selectedMovie == 4)
                                        z = 1;

                                    break;
                        }

                        ptr1[index].halls[g].seating = numtochar(input(1, "Enter seat arrangement (1)REGULAR and (2)ALTERNATE: "), 2); //changes the seating arrangment
                        
                        if(ptr1[index].halls[g].seating == 'A')
                        {
                            for(k = 0; k < 3; k++) //makes the seats have gaps
                            {
                                for(l = 0; l < 10; l++)
                                {
                                    if(((k == 0 || k == 2) && l % 2 == 0) || (k == 1 && l % 2 == 1))
                                    {
                                        ptr1[index].halls[g].time[time - 1].LowerBox[k][l] = 2;
                                        if(k < 2)
                                            ptr1[index].halls[g].time[time - 1].UpperBox[k][l] = 2;
                                    }
                                }
                            }
                        }
                    }
                }
                else
                    printf("\nThe hall is RESERVED. No other movies will be played besides the movie already added.");
            }
            else
            {
                printf("\nThe hall is ON-HOLD. No movies will be played.\n");
                return 0;
            }
        } while (satisfied != 2);

        if(z == 1 && date[index].movieday == 0) //when z equals to 1 and the movieday is equal to 0 then the days played will be added with 1
        {
            switch(time)
            {
                case 1: if(selectedMovie == 1)
                            ptr2->daysplayed[0] += 1;
                        else if(selectedMovie == 2)
                            ptr2->daysplayed[1] += 1;
                        else if(selectedMovie == 3)
                            ptr2->daysplayed[2] += 1;
                        else if(selectedMovie == 4)
                            ptr2->daysplayed[3] += 1;
                        else
                            ptr2->daysplayed[4] += 1;

                        break;
                case 2: if(selectedMovie == 1)
                            ptr2->daysplayed[0] += 1;
                        else if(selectedMovie == 2)
                            ptr2->daysplayed[1] += 1;
                        else if(selectedMovie == 3)
                            ptr2->daysplayed[2] += 1;
                        else if(selectedMovie == 4)
                            ptr2->daysplayed[3] += 1;
                        else
                            ptr2->daysplayed[4] += 1;

                        break;
                case 3: if(selectedMovie == 1)
                            ptr2->daysplayed[0] += 1;
                        else if(selectedMovie == 2)
                            ptr2->daysplayed[1] += 1;
                        else if(selectedMovie == 3)
                            ptr2->daysplayed[2] += 1;
                        else if(selectedMovie == 4)
                            ptr2->daysplayed[3] += 1;
                        else
                            ptr2->daysplayed[4] += 1;

                        break;
            }
        }
    }
    else
        printf("\nHall number doesn't exist!");
    
    return 0;
}

/* inputTS is a function that is used for entering the row and columns for occupying the seats.
@param *pOne has the address of the row in ticketSelling. Whatever the input for *pOne will be the value of row in ticketSelling.
@param *pTwo has the address of the col in ticket selling. Whatever the input for *pTwo will be the value of col in ticketSelling.
@param x has either the value 1 or 2. It's for the condition that would trigger the if condition for entering the column part.
@var z is used in the do-while loop. If z is equal to 1 then it stops the loop, meaning that the input for row and columns are correct.
@return 1 if do-while loop stopped.
Pre-condition: x needs to be either 1 or 2.
*/
int inputTS(int *pOne, int *pTwo, int x)
{
    int z = 0;

    do
    {
        printf("Enter Row: ");
        scanf("%d", &*pOne);

        if((x == 1 && *pOne >= 0 && *pOne <= 1) || (x == 2 && *pOne >= 0 && *pOne <= 2))
        {
            printf("Enter Column: ");
            scanf("%d", &*pTwo);
            if(!(*pTwo >= 0 && *pTwo <= 9))
                printf("ERROR: The input is incorrect.\n");
            else
                z = 1;    
        }
        else
            printf("ERROR: The input is incorrect.\n");
    }while(z == 0);

    return 1;
}

/*ticketSelling is the function for selling the tickets and getting the seats occupied.
@param *p1 holds the address of date[]
@param index is the index for *p1
@var choice1 holds the value of which hall is chosen.
@var choice2 holds the value of which time slot is chosen.
@var choice3 holds either one or two. One is where statements are executed for UpperBox[][] and Two is for the LowerBox[][].
@var vip is used for whether the person purchasing the tickets a vip or not. Subtracting the 10% of the cost.
@var max is for the how many times a ticket were purchased by the buyer.
@var row is the input of the user for the row seat.
@var col is the input of the user for the column seat.
@var end holds the value of 1. It is responsible for ending the while loop if end is equal to 2.
@var val will hold 1 that was returned by inputTS
@var x is part of a do-while loop. When x is not equal to 0, the do-while loop will stop.
@var q is the index of p1[index].halls[q]. It has the value of choice1 (hall number).
@var i is the index for the row of UpperBox[i][j] and LowerBox[i - 2][j].
@var j is the index for the column of UpperBox[i][j] and LowerBox[i - 2][j].
@var cost is the total cost of the price to be paid by the buyer of the tickets.
*/
int ticketSelling(struct dateSched *p1, int index)
{
    int choice1, choice2, choice3, vip, max = 0, row = -1, col = -1, end = 1, val, x, q, i, j;
    float cost = 0;
    while(max <= 5 && end != 2)
    {
        if(max >= 1)
        {
            printf("TOTAL COST: PHP %.2f\n", cost); //Will only print once the buyer had entered the rows and columns
            end = input(1, "Buy more tickets (1)yes or (2)no?: ");   
        }

        if(end != 2)
        {
            choice1 = input(3, "Enter movie hall: ");
            q = hallIdentifier(p1, choice1, index); //returns -1 if hall doesn't exist and if it does then it'll return the index in which where the hall is
            if(q != -1)
            {
                if(compareStruct(6, index, q) == 0 && compareStruct(7, index, q) == 0 && compareStruct(8, index, q) == 0) //when the condition is met, this means that there are no movies scheduled
                {
                    printf("\nSorry, that movie hall doesn't have anything scheduled at the moment.");
                    return 0;
                }
                    
                do
                {
                    choice2 = input(2, "Pick time (1)10:00 AM, (2)1:00 PM, (3)4:00 PM: "); //only 1 will be chosen if status is 'R'
                    
                    if(p1[index].halls[q].status == 'R')
                    {
                        if(choice2 == 1)
                            x = compareStruct(6, index, q);
                        else if(choice2 == 1)
                            x = compareStruct(7, index, q);
                        else
                            x = compareStruct(8, index, q);
                    }
                    else
                        x = 1;

                    if(x == 0 && p1[index].halls[q].status == 'R')
                        printf("\nOnly one slot is available");
                } while (x == 0);

                printf("\n\t\t\tSEATING\n");
                printf("\nSeat is available: [number.number]");
                printf("\nSeat is occupied: [X.X]");
                printf("\nSeat is a gap: [>.<]\n");
                for(i = 0; i < 5; i++) //prints the table of seats
                {
                    if(i == 2)
                        printf("\n");

                    for(j = 0; j < 10; j++)
                    {
                        if(i < 2)
                        {
                            if(i == 0 && j == 0 )//indicate that this is the upper box
                                printf("U");
                            else if(i == 1 && j == 0)
                                printf("P");

                            if(p1[index].halls[q].time[choice2 - 1].UpperBox[i][j] == 0) //if seat is available 
                                printf("[%d.%d] ", i, j);
                            else if(p1[index].halls[q].time[choice2 - 1].UpperBox[i][j] == 1) //if seat is occupied
                                printf("[X.X] ");
                            else //if this seat is a gap
                                printf("[>.<] ");
                        }
                        else
                        {
                            if(i == 2 && j == 0) //indicate that this is the lower box
                                printf("L");
                            else if(i == 3 && j == 0)
                                printf("O");
                            else if(i == 4 && j == 0)
                                printf("W");

                            if(p1[index].halls[q].time[choice2 - 1].LowerBox[i - 2][j] == 0)
                                printf("[%d.%d] ", i - 2, j);
                            else if(p1[index].halls[q].time[choice2 - 1].LowerBox[i - 2][j] == 1)
                                printf("[X.X] ");
                            else
                                printf("[>.<] ");
                        }
                    }
                    printf("\n");
                }
                printf("\n");

                choice3 = input(1, "(1)Upper Box or (2)Lower Box: ");

                if(choice3 == 1)
                {
                    val = inputTS(&row, &col, 1);

                    if(p1[index].halls[q].time[choice2 - 1].UpperBox[row][col] == 0) //if the seat is not occupied
                    {
                        p1[index].halls[q].time[choice2 - 1].UpperBox[row][col] = val;

                        if(p1[index].halls[q].status == 'R') //if the status of a hall is reserved then 500 will be added to the cost
                            cost += 500;               
                        else //if the status of a hall is not reserved(regular showing) then 400 will be added to the cost.
                            cost += 400;

                        max++;

                        p1[index].seatSold[choice2 - 1] += 1;
                    }
                    else if(p1[index].halls[q].time[choice2 - 1].UpperBox[row][col] == 2) //if the seat is a gap
                        printf("\nCan't sit here");
                    else
                        printf("\nSeat occupied!");
                }
                else
                {
                    val = inputTS(&row, &col, 2);

                    if(p1[index].halls[q].time[choice2 - 1].LowerBox[row][col] == 0) 
                    {
                        p1[index].halls[q].time[choice2 - 1].LowerBox[row][col] = val;

                        if(p1[index].halls[q].status == 'R') //if the status of a hall is reserved then 300 will be added to the cost
                            cost += 300;
                        else //if the status of a hall is not reserved(regular showing) then 200 will be added to the cost.
                            cost += 200;

                        max++;

                        p1[index].seatSold[choice2 - 1] += 1;
                    }
                    else if(p1[index].halls[q].time[choice2 - 1].LowerBox[row][col] == 2)
                        printf("\nCan't sit here");
                    else
                        printf("\nSeat occupied!");
                }
                    printf("\n");
            }
            else
            {
                printf("\nHall number doesn't exist!\n");
                return 0;
            }
        }
    }

    if(q != 1 || cost > 0)
    {
        printf("\nIs consumer a VIP?\n"); 
        vip = input(1, "Yes(1) or No(2): "); 
        if(vip == 1) //when the buyer is a vip subtract the 10% of the cost
            cost = cost - cost * 0.1;
        printf("TOTAL COST: PHP %.2f\n", cost);
    }

    return 0;
}

/*reports is a function that would print out the total seats sold, number of vacant seats per timeslot, active or inactive movies shown, and cinema halls ON-HOLD or RESERVED for the date entered.
@param index is the index for date[].
@var save holds the value of input on what mode of printing will be utilized.
@var i is inside a nested for loop in the second for-loop. It's also the index for date[index].halls[j].time[i].LowerBox[q][k].
@var j is in the outtermost for-loop of the nested for-loop. It serves as the index for date[index].halls[j].
@var k is in the third for-loop and serving as the column index for the condition if(date[index].halls[j].time[i].LowerBox[q][k] == 0) and if(date[index].halls[j].time[i].UpperBox[q][k] == 0 && q < 2).
@var t is in the innermost and serving as the row index for the condition if(date[index].halls[j].time[i].LowerBox[q][k] == 0) and if)date[index].halls[j].time[i].UpperBox[q][k] == 0 && q < 2).
@var ctrA is holds how many the cinemas are on-hold/reserved.
@var ctrS is contains how many seats are vacant.
@var strName[5] is for the movie names that were shown.
@var strRep is the string for the file name.
@var temp is the string that would be added to strRep using the strcat function.
@var str1 contains the string, "Active or inactive movies shown: " for the reporting on .txt file.
@var *fptr is the variable for the .txt file.
*/
void reports(int index)
{
    int save, i, j, k, t, q, ctrA = 0, ctrS = 0;

    String20 strName[5];
    String20 strRep, temp;
    sprintf(temp, "%d", date[index].movieday); //the string temp will get the value in date[index].movieday

    char str1[40];
    strcpy(str1, "Active or inactive movies shown: ");

    FILE *fptr;

    strcpy(strName[0], ""); //initialize strName[0 to 4] with ""
    strcpy(strName[1], "");
    strcpy(strName[2], "");
    strcpy(strName[3], "");
    strcpy(strName[4], "");

    for(j = 0; j < 4; j++)
    {
        if(date[index].halls[j].status == 'O' || date[index].halls[j].status == 'R') //when the status is either 'O' or 'R' then ctrA will add one to itself
            ctrA++; 
        
        for(i = 0; i < 3; i++)
        {
            if(date[index].halls[j].status == 'R' || date[index].halls[j].status == 'A') //this will trigger when status is either 'R' or 'A'
            {   
                for(q = 0; q < 3; q++)
                {
                    for(k = 0; k < 10; k++)
                    {
                        if(date[index].halls[j].time[i].LowerBox[q][k] == 0) //will add ctrS 1 to itself if the conditions are met
                        {
                            if(strcmp(date[index].halls[j].time1.name, "") != 0 && i == 0) 
                                ctrS++;
                            else if(strcmp(date[index].halls[j].time2.name, "") != 0 && i == 1)
                                ctrS++;
                            else if(strcmp(date[index].halls[j].time3.name, "") != 0 && i == 2)
                                ctrS++;
                        }                            
                        if(date[index].halls[j].time[i].UpperBox[q][k] == 0 && q < 2)
                        {
                            if(strcmp(date[index].halls[j].time1.name, "") != 0 && i == 0)
                                ctrS++;
                            else if(strcmp(date[index].halls[j].time2.name, "") != 0 && i == 1)
                                ctrS++;
                            else if(strcmp(date[index].halls[j].time3.name, "") != 0 && i == 2)
                                ctrS++;
                        }
                    }
                } 
            }

            if(compareStruct(6, index, j) == 1)
            {
                if(strcmp(c.movieOne.name, date[index].halls[j].time1.name) == 0) //if the movie in the time slot one is equal to movieOne then copy the name of c.movieOne.name to strName[0]
                    strcpy(strName[0], c.movieOne.name);

                if(strcmp(c.movieTwo.name, date[index].halls[j].time1.name) == 0)
                    strcpy(strName[1], c.movieTwo.name);

                if(strcmp(c.movieThree.name, date[index].halls[j].time1.name) == 0)
                    strcpy(strName[2], c.movieThree.name);

                if(strcmp(c.movieFour.name, date[index].halls[j].time1.name) == 0)
                    strcpy(strName[3], c.movieFour.name);

                if(strcmp(c.movieFive.name, date[index].halls[j].time1.name) == 0) //if the movie in the time slot one is equal to movieFive then copy the name of c.movieFive.name to strName[0]
                    strcpy(strName[4], c.movieFive.name);
            }
            if(compareStruct(7, index, j) == 1)
            {
                if(strcmp(c.movieOne.name, date[index].halls[j].time2.name) == 0)
                    strcpy(strName[0], c.movieOne.name);

                if(strcmp(c.movieTwo.name, date[index].halls[j].time2.name) == 0)
                    strcpy(strName[1], c.movieTwo.name);

                if(strcmp(c.movieThree.name, date[index].halls[j].time2.name) == 0)
                    strcpy(strName[2], c.movieThree.name);

                if(strcmp(c.movieFour.name, date[index].halls[j].time2.name) == 0)
                    strcpy(strName[3], c.movieFour.name);
                    
                if(strcmp(c.movieFive.name, date[index].halls[j].time2.name) == 0)
                    strcpy(strName[4], c.movieFive.name);
            }
            if(compareStruct(8, index, j) == 1)
            {
                if(strcmp(c.movieOne.name, date[index].halls[j].time3.name) == 0)
                    strcpy(strName[0], c.movieOne.name);

                if(strcmp(c.movieTwo.name, date[index].halls[j].time3.name) == 0)
                    strcpy(strName[1], c.movieTwo.name);

                if(strcmp(c.movieThree.name, date[index].halls[j].time3.name) == 0)
                    strcpy(strName[2], c.movieThree.name);

                if(strcmp(c.movieFour.name, date[index].halls[j].time3.name) == 0)
                    strcpy(strName[3], c.movieFour.name);
                    
                if(strcmp(c.movieFive.name, date[index].halls[j].time3.name) == 0)
                    strcpy(strName[4], c.movieFive.name);
            }
        }
    }

    save = input(1, "(1)Display on screen or (2)save in a text file?: ");

    if(save == 1) //display on screen
    {
        printf("\n=================================================");
        printf("\nREPORT FOR DAY %d", date[index].movieday);
        printf("\nTotal seats sold per timeslot:");
        printf("\nTime slot 1: %d", date[index].seatSold[0]);
        printf("\nTime slot 2: %d", date[index].seatSold[1]);
        printf("\nTime slot 3: %d", date[index].seatSold[2]);
        printf("\nNumber of vacant seats per timeslot: %d", ctrS);
        printf("\nActive or inactive movies shown: ");

        for(t = 0; t < 5; t++) //prints the shown movies
        {
            if(strcmp(strName[t], "") != 0)
                printf("\n%d. %s", t + 1, strName[t]);
        }

        printf("\nCinema halls ON-HOLD or RESERVED: %d", ctrA);
        printf("\n=================================================\n");
    }
    else //file output
    {
        strcpy(strRep, "report_");
        if(index > 99)
            strcat(strRep, "0");
        else if(index <= 9)
            strcat(strRep, "00");
        strcat(strRep, temp);
        strcat(strRep, ".txt"); //will be "report_num.txt"

        fptr = fopen(strRep, "w");

        fprintf(fptr, "REPORT FOR DAY %d", date[index].movieday); //print the ff. to the .txt file
        fprintf(fptr, "\nTotal seats sold per timeslot:");
        fprintf(fptr, "\nTime slot 1: %d", date[index].seatSold[0]);
        fprintf(fptr, "\nTime slot 2: %d", date[index].seatSold[1]);
        fprintf(fptr, "\nTime slot 3: %d", date[index].seatSold[2]);
        fprintf(fptr, "\nNumber of vacant seats per timeslot: %d", ctrS);
        fprintf(fptr, "\n%s", str1);

        for(t = 0; t < 5; t++)
        {
            if(strcmp(strName[t], "") != 0)
            {
                fprintf(fptr, "\n%d. ", t + 1);
                fprintf(fptr, "%s", strName[t]);
            }
        }

        fprintf(fptr, "\nCinema halls ON-HOLD or RESERVED: %d", ctrA);
        fclose(fptr); //close the .txt file
    }
}

/* The sort func from the name itself, sorts what is the supposed day movie day of the the index. Sorting the member .movieday from 1, 2, ..., 365.
@var i is used in the for loop and as the index of date[].
@var j is the variable for adding a number to date[i].movieday. Indicating that the movie house is open and that there will be a movie shown that day.
*/
void sort()
{
    int i, j = 1;
    for(i = 0; i < 365; i++) //every single day of 2023
    {
        if(date[i].period.month > 0)
        {
            date[i].movieday = j; //whatever the value of j it would be in date[i].movieday
            j++; //adding 1 to j = 1 will become j = 2
        }
    }
}

int main()
{
    int chosenFunc, end = 0, x, i, q, k, j, t;

    for(j = 0; j < 365; j++)
    {
        date[j].period.year = 2023; //set all .year of all date[] to 2023.
        for(t = 0; t < 4; t++)
        {
            strcpy(date[j].halls[t].time1.name, ""); //set movies of all time slots to ""
            strcpy(date[j].halls[t].time2.name, "");
            strcpy(date[j].halls[t].time3.name, "");
            date[j].halls[t].hallNumber = 0; //initialize all hall numbers to 0 in all indexes
            date[j].halls[t].status = 'V'; //set all statuses of all halls to vacnat
            if(t <= 2)
                date[j].seatSold[t] = 0; //initialize all seats sold to 0
        }
    }
        
    enter.year = 2023;

    strcpy(c.movieOne.name, ""); //set movies to ""
    strcpy(c.movieTwo.name, "");
    strcpy(c.movieThree.name, "");
    strcpy(c.movieFour.name, "");
    strcpy(c.movieFive.name, "");

    //introduction and instructions
    printf("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    printf("\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
    printf("\n\t\t\t\t\t\tWelcome To The Vendetta Movie House Ticketing System\n");
    printf("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    printf("\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
    printf("\nHOW TO USE THE VENDETTA MOVIE HOUSE TICKETING SYSTEM\n");
    printf("\nThe Vendetta Movie House Ticketing System is a program in which you will be able to do various things for the movie house.");
    printf("\nIt is responsible for the data within the movie house like the information about a movie, the halls, and etc.");
    printf("\nThere are four different functions. Firstly, the file maintenance function, handles the information about movie halls and movies.");
    printf("\nSecondly, the scheduling function, from the name itself creates and edits the schedule for what movie should be played during a specific time and date.");
    printf("\nThirdly, the ticket selling function, it reserves the seating for the person that would purchase the tickets. (NOTE: Only 5 tickets max per time slot)");
    printf("\nThe fourth one, creates the report in about different things. It reports the total seats sold per day, number of vacant seats per timeslot per day,");
    printf("\nlist of active or inactive movies shown, and list of cinema halls ON-HOLD or RESERVED. Could either be saved on screen or in .txt file.");
    printf("\nThe last one closes the program entirely.");
    printf("\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
    printf("\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");

    do
    {
        printf("\n-----------------------");
        printf("\nFILE MAINTENANCE   (1)");
        printf("\nSCHEDULING         (2)");
        printf("\nTICKET SELLING     (3)");
        printf("\nREPORTS            (4)");
        printf("\nEXIT               (5)\n");

        chosenFunc = input(4, "-----------------------\nFunction to use: ");
        switch(chosenFunc)
        {
            case 5: printf("\n\nThank you for using the Vendetta Movie House Ticketing System. \nHave a great day! :))"); //print if user wants to exit
                    printf("\n                :");
                    printf("\n                ;");
                    printf("\n               :");
                    printf("\n               ;");
                    printf("\n              /");
                    printf("\n            o/");
                    printf("\n          ._/\\___,");
                    printf("\n              \\");
                    printf("\n              /");
                    printf("\n              `");
                    end = 1; 

                    break;
            case 1: fileMaintenance(&c, date); break;
            case 2: i = dateInput();
                    date[i - 1].period.day = enter.day;
                    date[i - 1].period.month = enter.month;
                    schedule(date, &c, i - 1);
                    sort();
                    
                    break;
            case 3: i = dateInput();
                    ticketSelling(date, i - 1);

                    break;
            case 4: i = dateInput(); 
                    reports(i - 1);

                    break;
            default: printf("ERROR: The input is incorrect.\n"); break; //wrong input
        }
    }while((chosenFunc != 1 || chosenFunc != 2 || chosenFunc != 3 || chosenFunc != 4) && end == 0);
    
    return 0;
}

/****************************************************************************** 
This is to certify that this project is my own work, based on my personal 
efforts in studying and applying the concepts learned.  I have constructed  
the functions and their respective algorithms and corresponding code by  
myself.  The program was run, tested, and debugged by my own efforts.  I  
further certify that I have not copied in part or whole or otherwise  
plagiarized the work of other students and/or persons. 
 
BRENT JAN FONTANILLA SOAN - 12132101 - S11
******************************************************************************/ 