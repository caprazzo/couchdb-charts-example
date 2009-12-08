function(keys, values, rereduce) {
	function max(values) {
		var max = 0;
		for(var idx in values)
			if (values[idx]>max) max=values[idx];
		return max;
	}
	function min(values) {
		var min = null;
		for(var idx in values) {
			if (min==null) min=values[idx]
			else if (values[idx]<min) min=values[idx];
		}
		return min;
	}
	if (rereduce) {
		var max=null;
		var min=null;
		for (var idx in values) {
			var _min = values[idx].min;
			var _max = values[idx].max;
			
			if (min==null) { min=_min } else if (_min<min) { min=_min };			
			if (max==null) { max=_max } else if (_max>_max) { max=_max };
			
		}
		return [{min:min, max:max}]
	}
	else {
		return {min:min(values), max:max(values)};	
	}
	
}