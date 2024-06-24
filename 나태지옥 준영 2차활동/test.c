for (int i = 0; i < N; i++) {
    for (int j = 0; j < P; j++) {
        for (int k = 0; k < P; k++) {
            if (another_card[i][j].number == another1_card[i][k].number) {
                if (strcmp(another_card[i][j].shape, another1_card[i][k].shape) == 0) {
                    score[j] += k;
                }
            }
        }
    }
    for (int j = 0; j < P; j++) {
        printf("%d ", score[j]);
    }
    printf("\n");
}