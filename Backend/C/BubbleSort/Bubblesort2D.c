#include <stdio.h>
#define MAX 100

void swap (char arr[][MAX], int word) {
    int i, j, k = 0;
    char temp[MAX];
    for (k = 0; k < word; k++) {
        for (i = 0; i < word - 1; i++) {
            if (arr[i][0] > arr[i + 1][0]) {
                for (j = 0; j < MAX; j++) {
                    temp[j] = arr[i][j];
                    arr[i][j] = arr[i + 1][j];
                    arr[i + 1][j] = temp[j];
                }
            }
        }
    }
}
int main()
{
    int i, j, k = 0;
    int word = 6;
    char arr[MAX][MAX] = {
        {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'},
        {'e', 'e', 'e', 'e', 'e', 'e'},
        {'e', 'e', 'e', 'e', 'e', 'e'},
        {'e', 'e', 'e', 'e', 'e', 'e'},
        {'e', 'e', 'e', 'e', 'e', 'e'}
        
    };
    
    swap(arr, word);
    
    for (i = 0; i < 4; i++) {
        for (j = 0; j < MAX; j++) {
            printf("%c ", arr[i][j]);
        }
        printf("%d ", word_count[i]);
        printf("\n");
    }
    
    for (i = 0; i < 4; i++) {
        for (j = 0; j < MAX; j++) {
            printf("%c ", arr[i][j]);
        }
        printf("%d ", word_count[i]);
        printf("\n");
    }
}