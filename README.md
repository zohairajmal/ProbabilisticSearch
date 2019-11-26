# ProbabilisticSearch
For the probalistic search we need a sorted array, as well as the minimum and maximum value and the number of elements of the array.

* Calculation of the position is done by : (Value-Min)/((Max-Min)/(Num of Elements - 1))
* Now we check this position and go from this position in the required direction, whereby we proceed exponentially here, i.e. in the first step in one direction we go 2^0=1 one value further, in the second step 2^1=2 values, in third step 2^2=4 values further on, and so on.
* As soon as the program has to go once in the opposite direction (e.g.: our values were always too small up to now, but now they are suddenly too big), we have found an interval in which the searched value must lie. From this moment on we use the binary search as an auxiliary function.
