#!/bin/sh
# gnuplot.sh generates a plot of a series piped in stdin
(echo -e "set terminal png size 750, 500\nplot \"-\" using 1:2 with lines notitle"
cat -
echo -e "end") | gnuplot