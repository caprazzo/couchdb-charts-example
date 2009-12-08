function(keys, values, rereduce) {
	var output = { };
	if (rereduce) {
		for (idx in values) {
			if (values[idx].total !== undefined) {
		    	output.total += values[idx].total;
			} else if (values[idx].val !== undefined) {
				output.val = values[idx].val;
			}
		}
	}
	else {
		for (idx in values) {
			output.total += values[idx];
			output.val = values[idx];
		}
	}
	return output;
}