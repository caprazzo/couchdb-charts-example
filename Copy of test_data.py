import sys
import math
import random
import datetime

#def get_data(days):

wage = 1800
base = 10000
for i in range(0, int(sys.argv[1])):
	day = i%30
	if ( day == 0):
		base = base + wage
	base = base - math.tanh((30-day)/30.0)* random.randint(100, 168)
	print i,(int(base))
#return data
	
#print get_data()
#def print_gnuplot(days):
#	for (day, amount) in get_data(days):
#		print "%d %d" % (day, amount)

#def print_couchdb(tag, days):
#	date = datetime.datetime(2000, 01, 01)
#	docs = [ '{"_id":"%s-%s", "tag":"%s", "date":"%s", "amount":%d}' % 
#		(tag, day, tag, (date+datetime.timedelta(day)).strftime("%Y-%m-%d"), amount)
#		for (day, amount) in get_data(days)]
		
#	print '{"docs":[%s]}'% (',\n'.join(docs))
		


#print_gnuplot(3000)