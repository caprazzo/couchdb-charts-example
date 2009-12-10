// reduce.js
// this reduce function returns an array of objects
// {tot:total_value_for_group, count:elements_in_the_group}
// clients can than do tot/count to get the average for the group
// Keys are arrays [year][month][day], so count will always be 1 when group_level=3
function max(values, rereduce) {
	if (rereduce) {
		var vals = [];
		for(var idx in values) {
			vals[vals.length] = values[idx].max;
		}
		return max(vals, false);
	}
	else {
		var m = values[0];
		for (var idx in values) {
			if (values[idx] > m) m = values[idx]
		}
		return m;
	}
}
function(keys, values, rereduce) {
	var tot = 0;
	var count = 0;
	if (rereduce) {
		for (var idx in values) {
			tot += values[idx].tot;
			count += values[idx].count;
		}
	}
	else {
		tot = sum(values);
		count = values.length;
	}
	return {tot:tot, count:count, avg:tot/count};
}
