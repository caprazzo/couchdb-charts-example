# test_data.py. Usage: python test_data.py <simulation_length>
import sys
import random

days = int(sys.argv[1])
savings = 10000
pay = 2000
for i in range(0, days):
	if ( i%30 == 0):
		savings = savings + pay
	savings = savings - random.randint(0, pay/16) - 2
	print i, (int(savings))