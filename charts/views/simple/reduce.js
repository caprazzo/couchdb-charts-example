function(keys, values, rereduce) {
	

	
	if (!rereduce) {
		log('REDUCE');
		log({keys: keys});
		log({values: values});
		return [sum(values), values.length];
	}
	else {
		log('REREDUCE');
	}
}