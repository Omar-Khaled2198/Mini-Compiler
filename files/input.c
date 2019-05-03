int computefac(int num){
    int factorial;
    factorial = 1;
    if(num<0){
        return factorial;
    } else {
        int i;
        i=0;
        while(i<num){
            factorial=factorial*i; //factorial *=i
            i=i+1;
        }
    }

    return factorial;
}