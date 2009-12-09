# data_to_json.py. builds json output suitable for couchdb bulk operations
import sys
import datetime
date = datetime.datetime(2000, 01, 01)
tag = sys.argv[1]
print '{"docs":['
for line in sys.stdin:
	day, value = line.strip().split(' ')
	datestr = (date + datetime.timedelta(int(day))).strftime("%Y-%m-%d")
	if (day <> "0"): print ","
	sys.stdout.write('{"tag":"%s", "date":"%s", "amount":%s}'%(tag, datestr, value)),
print '\n]}',
	