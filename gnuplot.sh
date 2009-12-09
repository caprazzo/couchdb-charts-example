#!/bin/sh

(echo -e "set terminal png size 500, 500\nplot \"-\" using 1:2 with lines notitle"; python test_data.py; echo -e "end") | gnuplot > out.png