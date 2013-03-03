
#include <iostream>
#define ARRAY_SIZE(array) (sizeof((array))/sizeof((array[0])))
using namespace std;

void swap(int &x, int &y);

void sort(int num[]);
void merge(int source[], int destination[]);

int main()
{
    int x = 3;
    int y = 10;
    swap(x, y);
    cout << x << ',' << y << endl;
    int num[] = {2,3,4,5,2};
    int j[] = {32,6,2,14,12};
    sort(num);
    cout << num[4] << endl;
    //for(int x = 0; x < sizeof(num); x++)
     //   cout << num[x] << endl;
    merge(num, j);
    cout << j[5] << endl;
}

/**
    Merge copies the source array to the destination array.
 */
void merge(int source[], int destination[])
{
    int newarray[sizeof(source)/sizeof(int)+sizeof(destination)/sizeof(int)];
    for(int x = 0; x < ARRAY_SIZE(array); x++)
        newarray[x] = destination[x];
    for(int x = sizeof(destination)/sizeof(int); x < sizeof(source)/sizeof(int); x++)
        newarray[x] = source[x-sizeof(destination)/sizeof(int)];
    destination = newarray;
}

/**
    This sorts the array in reverse order. 
 */
void sort(int num[])
{
    int i, j;
    int numLength = sizeof(num);
    for(i = 1; (i <= numLength); i++)
        for (j=0; j < (numLength -1); j++)
            if (num[j+1] > num[j])
                swap(num[j+1], num[j]);
}

/**
    Swap swaps the arrays together.
 */
void swap(int &x, int &y)
{
    int temp = y;
    y = x;
    x = temp;
}