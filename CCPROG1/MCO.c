#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>
#define POT1_MAX 4 //MAX OF POT 1
#define POT2_MAX 2 //MAX OF POT 2
#define POT3_MAX 9 //MAX OF POT 3

void randRain(int *pP1, int *pP2, int *pP3);
int fullOrNot(int nGal1, int nGal2, int nGal3);
void pourPot(int *pPot_1, int *pPot_2, int *pPot_3, char cF, char cT);

//Include internal documentation (comments) in your program.

/*
PARAMETERS
*pPot_1: This is the parameter stores the number of gallons the first pot has.
*pPot_2: This is the parameter stores the number of gallons the second pot has.
*pPot_3: This is the parameter stores the number of gallons the third pot has.
cF: This parameter gives the character which pot will be poured.
cT: This parameter gives the character what pot will be filled.
*/

void 
pourPot(int *pPot_1, int *pPot_2, int *pPot_3, char cF, char cT)
{
    int i, j = 0;
    if (cF == '1') //Pours pot 1 to the chosen pot.
    {
        switch(cT)
        {
            case '2':
                     if(*pPot_1 > 0)
                     {
                        for(i = 1; *pPot_1 > 0 && *pPot_2 < POT2_MAX; *pPot_2 += i)
                            *pPot_1 -= i;       
                     }
                     break;

            case '3':
                    if(*pPot_1 > 0)
                     {
                        for(i = 1; *pPot_1 > 0 && *pPot_3 < POT3_MAX; *pPot_3 += i)
                            *pPot_1 -= i;
                     }
                     break;
        }
    }
    else if (cF == '2') //Pours pot 2 to the chosen pot.
    {
        switch(cT)
        {
            case '1':
                     if(*pPot_2 > 0)
                     {
                        for(i = 1; *pPot_2 > 0 && *pPot_1 < POT1_MAX; *pPot_1 += i)
                            *pPot_2 -= i;
                     }
                     break;

            case '3':
                     if(*pPot_2 > 0)
                     {
                        for(i = 1; *pPot_2 > 0 && *pPot_3 < POT3_MAX; *pPot_3 += i)
                            *pPot_2 -= i;
                     }
                     break;
        }
    }
    else if (cF == '3') //Pours pot 3 to the chosen pot.
    {
        switch(cT)
        {
            case '1':
                     if(*pPot_3 > 0)
                     {
                        for(i = 1; *pPot_3 > 0 && *pPot_1 < POT1_MAX; *pPot_1 += i)
                            *pPot_3 -= i;
                     }
                     break;

            case '2':
                     if(*pPot_3 > 0)
                     {
                        for(i = 1; *pPot_3 > 0 && *pPot_2 < POT2_MAX; *pPot_2 += i)
                            *pPot_3 -= i;
                     }
                     break;
        }
    }
}

/*
PARAMETERS
nGal1: This parameter contains the value of pot 1.
nGal2: This parameter contains the value of pot 2.
nGal3: This parameter contains the value of pot 3.
*/

int fullOrNot(int nGal1, int nGal2, int nGal3){
    int x, nPick_Pot;
    srand(time(NULL));
    do
    {
        nPick_Pot = rand() % 3; //generate number from 0-2
        nPick_Pot++; //add 1
        if (nPick_Pot == 1)
        {
            if (nGal1 == POT1_MAX)
                x = 0;
            else
                x = 1;
        }
        else if (nPick_Pot == 2)
        {
            if (nGal2 == POT2_MAX)
                x = 0;
            else
                x = 1;
        }
        else if (nPick_Pot == 3)
        {
            if (nGal3 == POT3_MAX)
                x = 0;
            else
                x = 1;
        }
    }while(x != 1);
    return nPick_Pot;
}

/*
PARAMETERS
*pP1: This parameter gets the value of pot 1 from the main.
*pP2: This parameter gets the value of pot 2 from the main.
*pP3: This parameter gets the value of pot 3 from the main.
*/

void 
randRain(int *pP1, int *pP2, int *pP3)
{
    int nChosen_Pot, i;
    int nAdd_Gal = 0, nTotal_Gal;

    srand(time(NULL));
    nTotal_Gal = rand() % 4 + 1; //Gets the value of how many gallons to add.
    for(i = 1; nAdd_Gal < nTotal_Gal; nAdd_Gal += i)
    {
        nChosen_Pot = fullOrNot(*pP1, *pP2, *pP3); //Calls fullOrNot to pick a pot that isn't full.
        switch(nChosen_Pot)
        {
            case 1: *pP1 += i;
                    break;

            case 2: *pP2 += i;
                    break;

            case 3: *pP3 += i;
                    break;
        }
    }
}

int main()
{
    char cStart_Choice, cFirst_Choice, cFrom, cTo, cEnd;
    int nPot_One = 0, nPot_Two = 0, nPot_Three = 9, x, k;
    int nDay = 1;
    x = 0;
    printf("==================================================================================\n");
    printf(" _______ _            _____       _       _    _               _____      _       \n");
    printf("|__   __| |          |  __ \\     (_)     | |  (_)             |  __ \\    | |      \n");
    printf("   | |  | |__   ___  | |  | |_ __ _ _ __ | | ___ _ __   __ _  | |__) |__ | |_ ___ \n");
    printf("   | |  | '_ \\ / _ \\ | |  | | '__| | '_ \\| |/ / | '_ \\ / _` | |  ___/ _ \\| __/ __|\n");
    printf("   | |  | | | |  __/ | |__| | |  | | | | |   <| | | | | (_| | | |  | (_) | |_\\__ \\ \n");
    printf("   |_|  |_| |_|\\___| |_____/|_|  |_|_| |_|_|\\_\\_|_| |_|\\__, | |_|   \\___/ \\__|___/\n");
    printf("                                                        __/ |                     \n");
    printf("                                                       |___/                      \n");
    printf("==================================================================================\n");
    printf("\n                   START(1)                    EXIT(0)\n\n");
    printf("Enter 1 or 0: ");
    scanf(" %c", &cStart_Choice);
    if(cStart_Choice == '1')
    {
        //INSTRUCTIONS
        printf("\n==================================================================================\n");
        printf("Hello player, Welcome!\n");
        printf("In this game there will be 3 villages and 3 pots. Everyday it will rain on the \n");
        printf("pots that aren't full filling them up to 4 gallons. The villages must survive on\n");
        printf("the next following days by drinking a gallon on their corresponding pot. \n");
        printf("POT 1 has a max of 4 gallons, POT 2 has a max of 2 gallons, and POT 3 has a max of\n");
        printf("9 gallons. In order to survive you must pour the pots evenly for the next days.\n");
        printf("If the pot becomes negative, then you lose. The days you survive is your score.\n");
        printf("==================================================================================\n");
        do
        {
            //Loop until a pot becomes -1
            printf("\nSELECT FROM THE OPTIONS\n");
            printf("OPTION (1) - Pour a pot\n");
            printf("OPTION (2) - Wait for the next day\n");
            printf("Enter 1 or 2: ");
            scanf(" %c", &cFirst_Choice);
            switch(cFirst_Choice)
            {
                case '1': //This case is for pouring the pots.
                        do
                        {
                            printf("Select what pot to pour: ");
                            scanf(" %c", &cFrom);
                            if (cFrom != '1' && cFrom != '2' && cFrom != '3')
                            {
                                printf("Invalid input please try again.\n");
                                cTo = '0';
                            }
                            else
                            {
                                do
                                {
                                    printf("Select what pot will pot %c be poured to: ", cFrom);
                                    scanf(" %c", &cTo);
                                    if (cTo != '1' && cTo != '2' && cTo != '3')
                                        printf("Invalid input please try again.\n");
                                    else if ((cTo == '1' && cFrom == '1') || (cTo == '2' && cFrom == '2') || (cTo == '3' && cFrom == '3'))
                                        printf("Cannot pour into the same spot.\n");
                                }while (cTo != '1' && cTo != '2' && cTo != '3');
                            }
                        }while (cTo != '1' && cTo != '2' && cTo != '3');
                        pourPot(&nPot_One, &nPot_Two, &nPot_Three, cFrom, cTo);
                        printf("\n+++++++++++++++++++++++++++++++++++++++++++\n");
                        printf("DAY %d\n", nDay);
                        printf("POT 1: %d\n", nPot_One);
                        printf("POT 2: %d\n", nPot_Two);
                        printf("POT 3: %d\n", nPot_Three);
                        printf("+++++++++++++++++++++++++++++++++++++++++++\n");
                        break;

                case '2': //Case for villagers drinking from the pots each, and raining on the pots.
                        for(k = 0; k <= 1; k++)
                        {
                            printf("\n+++++++++++++++++++++++++++++++++++++++++++\n");
                            if(k != 1)
                            {
                                printf("After drinking\n");
                                nPot_One--, nPot_Two--, nPot_Three--;
                            }
                            else if(k == 1)
                            {
                                printf("After rains\n");
                                randRain(&nPot_One, &nPot_Two, &nPot_Three);
                            }
                            printf("DAY %d\n", nDay);
                            printf("POT 1: %d\n", nPot_One);
                            printf("POT 2: %d\n", nPot_Two);
                            printf("POT 3: %d\n", nPot_Three);
                            printf("+++++++++++++++++++++++++++++++++++++++++++\n");
                            if(nPot_One < 0 || nPot_Two < 0 || nPot_Three < 0)
                            {
                                k = 2;
                                x = 1;
                            }
                        }
                        nDay++;
                        break;
                        
                default: //If input is invalid then print
                        printf("Invalid input please try again.\n");
                        break;
            }
            if (nPot_One < 0 || nPot_Two < 0 || nPot_Three < 0)
                x = 1;
        }while(x == 0);
        //END GAME
        nDay--;
        printf("_________________________________________________________________\n");
        printf("      __                              __                       / \n");
        printf("    /    )                          /    )                    /  \n");
        printf("---/---------__---_--_----__-------/----/---------__---)__---/---\n");
        printf("  /  --,   /   ) / /  ) /___)     /    /   | /  /___) /   ) /    \n");
        printf("_(____/___(___(_/_/__/_(___ _____(____/____|/__(___ _/_____o_____\n\n");
        printf("GAME ENDED\n");
        printf("DAYS SURVIVED: %d\n", nDay);
        printf("\nTHANK YOU FOR PLAYING THE GAME!");
        printf("\nCLICK ANY KEY TO EXIT.");

        while(1) {
            if(kbhit())
                break;
        }
    }
    return 0;
}

/******************************************************************************
This is to certify that this project is my own work, based on my personal
efforts in studying and applying the concepts learned. I have constructed
the functions and their respective algorithms and corresponding code by
myself. The program was run, tested, and debugged by my own efforts. I
further certify that I have not copied in part or whole or otherwise
plagiarized the work of other students and/or persons.
Brent Jan F. Soan - 12132101 - S16
******************************************************************************/
