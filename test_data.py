import math
import random
import datetime

def sigmoid(x):
    return math.tanh(x)


def get_data(days):
	data = []
	base = 0
	for i in range(0, days):
		day = i%30
		if ( day == 0):
			base = base + 1800
		base = base - math.tanh((30-day)/30.0)* random.randint(0, 250)
		data.append((i, base))
	return data
		
def print_gnuplot(days):
	for (day, amount) in get_data(days):
		print "%d %d" % (day, amount)

def print_couchdb(tag, days):
	date = datetime.datetime(2000, 01, 01)
	docs = [ '{"_id":"%s-%s", "tag":"%s", "date":"%s", "amount":%d}' % 
		(tag, day, tag, (date+datetime.timedelta(day)).strftime("%Y-%m-%d"), amount)
		for (day, amount) in get_data(days)]
		
	print '{"docs":[%s]}'% (',\n'.join(docs))
		
	
